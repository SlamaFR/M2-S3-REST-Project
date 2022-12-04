package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BikeStorage extends Remote {

    RemoteOptional<Bike> findById(UUID bikeId) throws RemoteException;

    Response<Set<Bike>> allBikes() throws RemoteException;

    Response<Set<Bike>> availableBikes() throws RemoteException;

    Response<Set<Bike>> userOwnedBikes(UUID sessionToken) throws RemoteException;

    Response<Set<Bike>> userOrderedBikes(UUID sessionToken) throws RemoteException;

    Response<List<BikeState>> orderBikes(List<UUID> bikeIds, UUID sessionToken) throws RemoteException;

    Response<List<String>> notifications(UUID sessionToken) throws RemoteException;

    Response<Void> returnBike(BikeOrder order, UUID sessionToken) throws RemoteException;

    Response<Bike> addOwnedBike(UUID sessionToken) throws RemoteException;

    Response<Void> removeOwnedBike(UUID bikeId, UUID sessionToken) throws RemoteException;

    Response<Void> changeBikeOwner(UUID bikeId, UUID sessionToken) throws RemoteException;

}
