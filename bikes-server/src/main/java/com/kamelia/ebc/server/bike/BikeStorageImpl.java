package com.kamelia.ebc.server.bike;

import com.kamelia.ebc.common.base.*;
import com.kamelia.ebc.common.util.NotFoundException;
import com.kamelia.ebc.common.util.Pair;
import com.kamelia.ebc.common.util.UnauthorizedException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class BikeStorageImpl extends UnicastRemoteObject implements BikeStorage {

    private final HashMap<UUID, BikeImpl> idToBike;
    private final HashMap<UUID, HashSet<Bike>> userIdToOwnedBikes;
    private final HashMap<UUID, HashSet<Bike>> userIdToOrderedBikes;
    private final HashMap<UUID, ArrayDeque<Pair<Notifier, UUID>>> bikeToOrderQueue;
    private final UserStorage userStorage;

    public BikeStorageImpl(UserStorage userStorage) throws RemoteException {
        Objects.requireNonNull(userStorage);
        this.idToBike = new HashMap<>();
        this.userIdToOwnedBikes = new HashMap<>();
        this.userIdToOrderedBikes = new HashMap<>();
        this.bikeToOrderQueue = new HashMap<>();
        this.userStorage = userStorage;
    }

    @Override
    public RemoteOptional<Bike> findById(UUID bikeId) throws RemoteException {
        Objects.requireNonNull(bikeId);
        return RemoteOptional.ofNullable(idToBike.get(bikeId));
    }

    @Override
    public Response<Set<Bike>> allBikes() throws RemoteException {
        return Response.ok(new HashSet<>(idToBike.values()));
    }

    @Override
    public Response<Set<Bike>> availableBikes() throws RemoteException {
        var bikes = new HashSet<Bike>();
        for (var bike : idToBike.values()) {
            if (bike.orderer() == null) {
                bikes.add(bike);
            }
        }
        return Response.ok(bikes);
    }

    @Override
    public Response<Bike> addOwnedBike(User owner, UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(owner);
        Objects.requireNonNull(sessionToken);

        if (!checkAuth(owner, sessionToken)) {
            return notAuthenticated();
        }

        var id = UUID.randomUUID();
        var bike = new BikeImpl(id, owner);
        idToBike.put(bike.id(), bike);
        return Response.ok(bike);
    }

    @Override
    public Response<Void> removeOwnedBike(UUID bikeId, User owner, UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(owner);

        if (!checkAuth(owner, sessionToken)) {
            return notAuthenticated();
        }

        var bike = idToBike.get(bikeId);

        try {
            idToBike.compute(bikeId, (id, old) -> {
                if (old == null) { // check if bike exists
                    throw new NotFoundException();
                }

                try { // check if bike is owned by user
                    if (!bike.owner().id().equals(owner.id())) {
                        throw new UnauthorizedException();
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

                return null;
            });
        } catch (NotFoundException e) {
            return Response.notFound("Bike not found");
        } catch (UnauthorizedException e) {
            return Response.unauthorized("Bike is not owned by user");
        }

        bike.setRemovedFromOrders();
        bikeToOrderQueue.remove(bike.id());

        if (bike.orderer().isPresent()) {
            return Response.ok("Bike will be removed when the current orderer will return it", null);
        }

        return Response.ok(null);
    }

    @Override
    public Response<BikeState> orderBike(
        UUID bikeId,
        NotifiableUser notifiableUser,
        UUID sessionToken
    ) throws RemoteException {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(notifiableUser);

        var user = notifiableUser.user();
        if (!checkAuth(user, sessionToken)) {
            return notAuthenticated();
        }

        var bike = idToBike.get(bikeId);
        if (bike == null) {
            return Response.notFound("Bike not found");
        }

        if (bike.removedFromOrders()) {
            return Response.unauthorized("Owner has removed bike from orders");
        }

        if (bike.orderer().isEmpty()) {
            orderBikeForUser(bike, user);
            return Response.ok(BikeState.AVAILABLE);
        }

        // bike is already ordered, add user to queue
        bikeToOrderQueue.compute(bike.id(), (ignored, queue) -> {
            if (queue == null) {
                queue = new ArrayDeque<>();
            }
            try {
                queue.add(new Pair<>(notifiableUser.notifiable(), user.id()));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            return queue;
        });

        return Response.ok(BikeState.ALREADY_ORDERED);
    }

    @Override
    public Response<Void> returnBike(BikeOrder order, User user, UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        Objects.requireNonNull(user);
        Objects.requireNonNull(sessionToken);

        if (!checkAuth(user, sessionToken)) {
            return notAuthenticated();
        }

        var bike = order.bike();

        // check if provided bike is the same as the stored bike
        var storedBike = idToBike.get(bike.id());

        if (!storedBike.orderer().contentEquals(user)) { // check if user is the orderer
            return Response.unauthorized("User is not the orderer");
        }

        if (!order.author().equals(user)) { // check if user is the author of the order
            return Response.unauthorized("User is not the author");
        }

        storedBike.addOrder(order); // add the order to the bike

        // remove the bike from the orders of the user
        userIdToOrderedBikes.computeIfPresent(user.id(), (ignored, bikes) -> {
            bikes.remove(storedBike);
            return bikes;
        });
        orderBikeForNextUserInQueue(storedBike);

        return Response.ok(null);
    }

    @Override
    public RemoteOptional<Set<Bike>> userOwnedBikes(User user) throws RemoteException {
        Objects.requireNonNull(user);
        return userBikes(user, userIdToOwnedBikes);
    }

    @Override
    public RemoteOptional<Set<Bike>> userOrderedBikes(User user) throws RemoteException {
        Objects.requireNonNull(user);
        return userBikes(user, userIdToOrderedBikes);
    }

    private boolean checkAuth(User user, UUID token) throws RemoteException {
        return userStorage.isAuthenticated(user.id(), token);
    }

    private void orderBikeForNextUserInQueue(BikeImpl bike) throws RemoteException {
        var queue = bikeToOrderQueue.get(bike.id());
        if (queue == null) {
            return;
        }

        var nextUser = queue.poll();
        if (nextUser == null) {
            return;
        }

        var notifier = nextUser.first();
        var id = nextUser.second();
        var user = userStorage.findById(id);
        if (user.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        orderBikeForUser(bike, user.get());
        notifier.notifyOnReturnedBike(bike);
    }

    private void orderBikeForUser(BikeImpl bike, User orderer) throws RemoteException {
        bike.setOrderer(orderer);
        userIdToOrderedBikes.compute(orderer.id(), (ignored, bikes) -> {
            if (bikes == null) {
                bikes = new HashSet<>();
            }
            bikes.add(bike);
            return bikes;
        });
    }

    private static <T> Response<T> notAuthenticated() {
        return Response.unauthorized("User is not authenticated");
    }

    private static RemoteOptional<Set<Bike>> userBikes(
        User user,
        HashMap<UUID, HashSet<Bike>> map
    ) throws RemoteException {
        var set = map.get(user.id());
        return set == null ? RemoteOptional.empty() : RemoteOptional.ofNullable(Set.copyOf(set));
    }
}
