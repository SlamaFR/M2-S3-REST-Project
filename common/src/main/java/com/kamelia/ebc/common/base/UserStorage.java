package com.kamelia.ebc.common.base;

import com.kamelia.ebc.common.util.Pair;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface UserStorage extends Remote {

    RemoteOptional<User> findById(UUID id) throws RemoteException;

    RemoteOptional<User> findByUsername(String username) throws RemoteException;

    Response<NotifiableUser> save(String username, String password, Notifier notifier) throws RemoteException;

    Response<Pair<NotifiableUser, UUID>> login(String username, String password) throws RemoteException;

    void logout(UUID sessionId) throws RemoteException;

    boolean isAuthenticated(UUID userId, UUID sessionToken) throws RemoteException;

}
