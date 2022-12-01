package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.UUID;

public interface BikeStorage extends Remote {

    RemoteOptional<Bike> findById(UUID bikeId) throws RemoteException;

    Response<Set<Bike>> allBikes() throws RemoteException;

    Response<Set<Bike>> availableBikes() throws RemoteException;

    RemoteOptional<Set<Bike>> userOwnedBikes(User user) throws RemoteException;

    RemoteOptional<Set<Bike>> userOrderedBikes(User user) throws RemoteException;

    Response<BikeState> orderBike(UUID bikeId, UUID sessionToken) throws RemoteException;

    Response<Void> returnBike(BikeOrder order, UUID sessionToken) throws RemoteException;

    Response<Bike> addOwnedBike(User owner, UUID sessionToken) throws RemoteException;

    Response<Void> removeOwnedBike(UUID bikeId, UUID sessionToken) throws RemoteException;

}
