package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface UserStorage extends Remote {

    User findById(UUID id) throws RemoteException;

    User findByUsername(String username) throws RemoteException;

    User save(String username, String password) throws RemoteException;

    UUID login(String username, String password) throws RemoteException;

}
