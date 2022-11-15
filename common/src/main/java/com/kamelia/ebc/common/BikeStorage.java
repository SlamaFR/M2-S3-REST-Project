package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface BikeStorage extends Remote {

    RemoteOptional<Bike> findById(UUID bikeId) throws RemoteException;

    Response<Bike> save(User owner, UUID token) throws RemoteException;

    Response<Void> deleteById(UUID bikeId, User user) throws RemoteException;

    Response<BikeState> order(UUID bikeId, User user) throws RemoteException;

    Response<Void> returnBike(UUID bikeId, User user) throws RemoteException;
}
