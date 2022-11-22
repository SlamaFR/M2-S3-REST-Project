package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface Bike extends Remote {

    UUID id() throws RemoteException;

    User owner() throws RemoteException;

    boolean removedFromOrders() throws RemoteException;

    RemoteOptional<User> orderer() throws RemoteException;

    List<BikeOrder> ordersHistory() throws RemoteException;

}
