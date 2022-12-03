package com.kamelia.ebc.server.bike;

import com.kamelia.ebc.common.base.*;
import com.kamelia.ebc.common.util.NotFoundException;
import com.kamelia.ebc.common.util.UnauthorizedException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BikeStorageImpl extends UnicastRemoteObject implements BikeStorage {

    private final HashMap<UUID, BikeImpl> idToBike;
    private final HashMap<UUID, Set<UUID>> userIdToOwnedBikes;
    private final HashMap<UUID, Set<UUID>> userIdToOrderedBikes;
    private final HashMap<UUID, ArrayDeque<UUID>> bikeToOrderQueue;
    private final HashMap<UUID, List<String>> userIdToNotifications;
    private final UserStorage userStorage;

    public BikeStorageImpl(UserStorage userStorage) throws RemoteException {
        Objects.requireNonNull(userStorage);
        this.idToBike = new HashMap<>();
        this.userIdToOwnedBikes = new HashMap<>();
        this.userIdToOrderedBikes = new HashMap<>();
        this.bikeToOrderQueue = new HashMap<>();
        this.userIdToNotifications = new HashMap<>();
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
    public Response<Bike> addOwnedBike(UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);

        var opt = userStorage.isAuthenticated(sessionToken);
        if (opt.isEmpty()) {
            return notAuthenticated();
        }
        var user = userStorage.findById(opt.get()).get();

        var id = UUID.randomUUID();
        var bike = new BikeImpl(id, user);
        idToBike.put(bike.id(), bike);
        return Response.ok(bike);
    }

    @Override
    public Response<Void> removeOwnedBike(UUID bikeId, UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(sessionToken);

        var opt = userStorage.isAuthenticated(sessionToken);
        if (opt.isEmpty()) {
            return notAuthenticated();
        }
        var user = userStorage.findById(opt.get()).get();

        var bike = idToBike.get(bikeId);

        try {
            idToBike.compute(bikeId, (id, old) -> {
                if (old == null) { // check if bike exists
                    throw new NotFoundException();
                }

                try { // check if bike is owned by user
                    if (!bike.owner().id().equals(user.id())) {
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
    public Response<List<BikeState>> orderBikes(List<UUID> bikeIds, UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(bikeIds);
        Objects.requireNonNull(sessionToken);
        var opt = userStorage.isAuthenticated(sessionToken);
        if (opt.isEmpty()) {
            return notAuthenticated();
        }
        var user = userStorage.findById(opt.get()).get();

        return bikeIds.stream()
            .map(id -> orderBikeUnchecked(user, id))
            .filter(Objects::nonNull)
            .collect(COLLECTOR);
    }

    private BikeState orderBikeUnchecked(User user, UUID bikeId) {
        try {
            return orderBike(user, bikeId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private BikeState orderBike(User user, UUID bikeId) throws RemoteException {
        var bike = idToBike.get(bikeId);
        if (bike == null) {
            return null;
        }

        if (bike.removedFromOrders()) {
            return null;
        }

        if (bike.orderer().isEmpty()) {
            orderBikeForUser(bike, user);
            return BikeState.AVAILABLE;
        }

        // bike is already ordered, add user to queue
        bikeToOrderQueue.compute(bike.id(), (ignored, queue) -> {
            if (queue == null) {
                queue = new ArrayDeque<>();
            }
            try {
                queue.add(user.id());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            return queue;
        });

        return BikeState.ALREADY_ORDERED;
    }

    @Override
    public Response<Void> returnBike(BikeOrder order, UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        Objects.requireNonNull(sessionToken);

        var opt = userStorage.isAuthenticated(sessionToken);
        if (opt.isEmpty()) {
            return notAuthenticated();
        }

        var user = userStorage.findById(opt.get()).get();
        var bike = order.bike();
        var storedBike = idToBike.get(bike.id());

        if (!storedBike.orderer().contentEquals(user)) { // check if user is the orderer
            return Response.unauthorized("User is not the orderer");
        }

        storedBike.addOrder(user, order); // add the order to the bike

        // remove the bike from the orders of the user
        userIdToOrderedBikes.computeIfPresent(user.id(), (ignored, bikes) -> {
            try {
                bikes.remove(storedBike.id());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            return bikes;
        });
        orderBikeForNextUserInQueue(storedBike);

        return Response.ok(null);
    }

    @Override
    public Response<Set<Bike>> userOwnedBikes(UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        var opt = userStorage.isAuthenticated(sessionToken);
        if (opt.isEmpty()) {
            return notAuthenticated();
        }
        return mapIdsToBikes(opt.get(), userIdToOwnedBikes);
    }

    @Override
    public Response<Set<Bike>> userOrderedBikes(UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        var opt = userStorage.isAuthenticated(sessionToken);
        if (opt.isEmpty()) {
            return notAuthenticated();
        }
        return mapIdsToBikes(opt.get(), userIdToOrderedBikes);
    }

    private void orderBikeForNextUserInQueue(BikeImpl bike) throws RemoteException {
        var queue = bikeToOrderQueue.get(bike.id());
        bike.setOrderer(null);
        if (queue == null) {
            return;
        }

        var nextId = queue.poll();
        if (nextId == null) {
            return;
        }

        var opt = userStorage.findById(nextId);
        if (opt.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        var user = opt.get();
        orderBikeForUser(bike, user);
        userIdToNotifications.computeIfAbsent(user.id(), (ignored) -> new ArrayList<>())
            .add("Ouais mon reuf le vélo " + bike.id() +" est prêt");
    }

    private void orderBikeForUser(BikeImpl bike, User orderer) throws RemoteException {
        bike.setOrderer(orderer);
        userIdToOrderedBikes.compute(orderer.id(), (ignored, bikes) -> {
            if (bikes == null) {
                bikes = new HashSet<>();
            }
            try {
                bikes.add(bike.id());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            return bikes;
        });
    }

    private static <T> Response<T> notAuthenticated() {
        return Response.unauthorized("User is not authenticated");
    }

    @Override
    public Response<List<String>> notifications(UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);

        var opt = userStorage.isAuthenticated(sessionToken);
        if (opt.isEmpty()) {
            return notAuthenticated();
        }
        var user = userStorage.findById(opt.get()).get();

        return Response.ok(userIdToNotifications.get(user.id()));
    }

    private Response<Set<Bike>> mapIdsToBikes(UUID userId, Map<UUID, Set<UUID>> map) {
        var set = map.getOrDefault(userId, Set.of())
            .stream()
            .map(id -> (Bike) idToBike.get(id))
            .collect(Collectors.toSet());
        return Response.ok(set);
    }

    private static final Collector<BikeState, ArrayList<BikeState>, Response<List<BikeState>>> COLLECTOR = Collector.of(
        ArrayList::new,
        ArrayList::add,
        (left, right) -> { left.addAll(right); return left; },
        Response::ok
    );
}
