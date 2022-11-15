package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.UUID;

public interface UserDataHolder extends Remote {

    UUID id() throws RemoteException;

    String username() throws RemoteException;

    Kind kind() throws RemoteException;

    Set<Bike> ownedBikes() throws RemoteException;

    Set<Bike> orderedBikes() throws RemoteException;

    Set<BikeOrder> ordersHistory() throws RemoteException;

    enum Kind {
        STUDENT, EMPLOYEE, EIFFEL_BIKE_CORP
    }

}
