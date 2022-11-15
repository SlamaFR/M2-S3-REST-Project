package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.UUID;

public interface BikeStorage extends Remote {

    Optional<Bike> findById(UUID bikeId) throws RemoteException;

    Bike save(User owner, UUID token) throws RemoteException;

    void deleteById(UUID bikeId, User user, UUID token) throws RemoteException;

    BikeState order(UUID bikeId, User user, UUID token) throws RemoteException;
}
