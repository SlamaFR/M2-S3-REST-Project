package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.UUID;

public interface User extends Remote {

    UUID id() throws RemoteException;

    String username() throws RemoteException;

    Set<Bike> ownedBikes() throws RemoteException;

    Set<Bike> orderedBikes() throws RemoteException;

    Set<BikeOrder> orders() throws RemoteException;

}
