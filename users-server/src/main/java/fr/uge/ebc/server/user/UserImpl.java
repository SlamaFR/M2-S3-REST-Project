package fr.uge.ebc.server.user;

import com.kamelia.ebc.common.base.User;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.UUID;

class UserImpl extends UnicastRemoteObject implements User {

    private final UUID id;
    private final String username;
    private final String hashedPassword;

    public UserImpl(UUID id, String username, String hashedPassword) throws RemoteException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(username);
        Objects.requireNonNull(hashedPassword);
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    String hashedPassword() {
        return hashedPassword;
    }

    @Override
    public UUID id() throws RemoteException {
        return id;
    }

    @Override
    public String username() throws RemoteException {
        return username;
    }

}
