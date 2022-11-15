package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Bike extends Remote {

    UUID id() throws RemoteException;

    User owner() throws RemoteException;

    Optional<User> orderer() throws RemoteException;

    List<BikeOrder> ordersHistory() throws RemoteException;

}
