package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface UserStorage extends Remote {

    RemoteOptional<UserDataHolder> findById(UUID id) throws RemoteException;

    RemoteOptional<UserDataHolder> findByUsername(String username) throws RemoteException;

    User save(String username, User.Kind kind, String password) throws RemoteException;

    Response<User> login(String username, String password) throws RemoteException;

    void logout(UUID sessionId) throws RemoteException;

    boolean isAuthorized(User user, UUID sessionId) throws RemoteException;

}
