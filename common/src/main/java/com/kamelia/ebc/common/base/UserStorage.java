package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface UserStorage extends Remote {

    RemoteOptional<User> findById(UUID id) throws RemoteException;

    RemoteOptional<User> findByUsername(String username) throws RemoteException;

    Response<User> save(String username, String password) throws RemoteException;

    Response<Pair<UUID, UUID>> login(String username, String password) throws RemoteException;

    void logout(UUID sessionId) throws RemoteException;

    boolean isAuthenticated(UUID userId, UUID sessionToken) throws RemoteException;

}
