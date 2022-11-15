package com.kamelia.ebc.server;

import com.kamelia.ebc.common.Bike;
import com.kamelia.ebc.common.BikeOrder;
import com.kamelia.ebc.common.UserDataHolder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

class AbstractUser extends UnicastRemoteObject implements UserDataHolder {

    private final UUID id;
    private final String username;
    private final UserDataHolder.Kind kind;
    protected final HashSet<Bike> ownedBikes;
    protected final HashSet<Bike> orderedBikes;
    protected final HashSet<BikeOrder> ordersHistory;

    AbstractUser(String username, UserDataHolder.Kind kind) throws RemoteException {
        super();
        Objects.requireNonNull(username);
        Objects.requireNonNull(kind);

        this.id = UUID.randomUUID();
        this.username = username;
        this.kind = kind;
        this.ownedBikes = new HashSet<>();
        this.orderedBikes = new HashSet<>();
        this.ordersHistory = new HashSet<>();
    }

    @Override
    public UUID id() throws RemoteException {
        return id;
    }

    @Override
    public String username() throws RemoteException {
        return username;
    }

    @Override
    public UserDataHolder.Kind kind() throws RemoteException {
        return kind;
    }

    @Override
    public Set<Bike> ownedBikes() throws RemoteException {
        return Set.copyOf(ownedBikes);
    }

    @Override
    public Set<Bike> orderedBikes() throws RemoteException {
        return Set.copyOf(orderedBikes);
    }

    @Override
    public Set<BikeOrder> ordersHistory() throws RemoteException {
        return Set.copyOf(ordersHistory);
    }

}
