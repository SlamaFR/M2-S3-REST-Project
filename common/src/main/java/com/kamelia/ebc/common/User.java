package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.UUID;

public interface User extends UserDataHolder {

    void orderBike(UUID bikeId) throws RemoteException;

    void returnBike(UUID bikeId, Comment comment, ReturnState state) throws RemoteException;

    void addOwnedBike(Bike bike) throws RemoteException;

    void removeOwnedBike(Bike bike) throws RemoteException;
}
