package com.kamelia.ebc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.UUID;

public interface UserStorage extends Remote {

    Optional<User> findById(UUID id) throws RemoteException;

    Optional<User> findByUsername(String username) throws RemoteException;

    User save(String username, User.Kind kind, String password) throws RemoteException;

    UUID login(String username, String password) throws RemoteException;

}
