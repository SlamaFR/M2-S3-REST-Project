package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface UserStorage extends Remote {

    RemoteOptional<User> findById(UUID id) throws RemoteException;

    RemoteOptional<User> findByUsername(String username) throws RemoteException;

    boolean save(String username, String password) throws RemoteException;

    Response<UUID> login(String username, String password) throws RemoteException;

    Response<List<String>> notifications(UUID sessionToken) throws RemoteException;

    void logout(UUID sessionToken) throws RemoteException;

    RemoteOptional<UUID> isAuthenticated(UUID sessionToken) throws RemoteException;

    void addNotification(UUID userId, String notification) throws RemoteException;

}
