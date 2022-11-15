package com.kamelia.ebc.server;

import com.kamelia.ebc.common.Bike;
import com.kamelia.ebc.common.BikeState;
import com.kamelia.ebc.common.BikeStorage;
import com.kamelia.ebc.common.RemoteOptional;
import com.kamelia.ebc.common.Response;
import com.kamelia.ebc.common.User;
import com.kamelia.ebc.common.UserStorage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BikeStorageImpl extends UnicastRemoteObject implements BikeStorage {

    private final HashMap<UUID, BikeImpl> bikesById;
    private final UserStorage userStorage;

    public BikeStorageImpl() throws RemoteException {
        super();
        this.bikesById = new HashMap<>();
        this.userStorage = Server.instance().userStorage();
    }

    @Override
    public RemoteOptional<Bike> findById(UUID bikeId) throws RemoteException {
        Objects.requireNonNull(bikeId);
        return RemoteOptional.ofNullable(bikesById.get(bikeId));
    }

    @Override
    public Response<Bike> save(User owner, UUID token) throws RemoteException {
        Objects.requireNonNull(owner);
        Objects.requireNonNull(token);

        if (!userStorage.isAuthorized(owner, token)) {
            return Response.unauthorized();
        }

        var bike = new BikeImpl(owner);
        owner.addOwnedBike(bike);
        bikesById.put(bike.id(), bike);
        return Response.ok(bike);
    }

    @Override
    public Response<Void> deleteById(UUID bikeId, User owner, UUID token) throws RemoteException {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(owner);
        Objects.requireNonNull(token);

        if (!userStorage.isAuthorized(owner, token)) {
            return Response.unauthorized();
        }

        var bike = bikesById.get(bikeId);
        if (bike == null) {
            return Response.notFound();
        }

        if (!bike.owner().id().equals(owner.id())) {
            return Response.forbidden();
        }

        bikesById.remove(bikeId);
        owner.removeOwnedBike(bike);

        return Response.ok(null);
    }

    @Override
    public Response<BikeState> order(UUID bikeId, User orderer) throws RemoteException {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(orderer);

        var bike = bikesById.get(bikeId);
        if (bike == null) {
            return Response.notFound();
        }

        if (bike.orderer().isPresent()) {
            return Response.ok(BikeState.ALREADY_ORDERED);
        }

        bike.setOrderer(orderer);
        return Response.ok(BikeState.AVAILABLE);
    }

    @Override
    public Response<Void> returnBike(UUID bikeId, User user, UUID token) throws RemoteException {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(user);
        Objects.requireNonNull(token);

        if (!userStorage.isAuthorized(user, token)) {
            return Response.unauthorized();
        }

        Bike bikeFound = null;
        for (var bike : user.orderedBikes()) {
            if (bikeId.equals(bike.id())) {
                bikeFound = bike;
                break;
            }
        }
        if (bikeFound == null) {
            return Response.notFound();
        }

        ((UserImpl) user).removeOrderedBike(bikeFound);
        ((BikeImpl) bikeFound).setOrderer(null);

        return Response.ok(null);
    }
}
