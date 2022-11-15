package com.kamelia.ebc.server;

import com.kamelia.ebc.common.Bike;
import com.kamelia.ebc.common.BikeOrder;
import com.kamelia.ebc.common.RemoteOptional;
import com.kamelia.ebc.common.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class BikeImpl extends UnicastRemoteObject implements Bike {

    private final UUID id;
    private final User owner;
    private final ArrayList<BikeOrder> ordersHistory;
    private User orderer;

    BikeImpl(User owner) throws RemoteException {
        super();
        Objects.requireNonNull(owner);

        this.id = UUID.randomUUID();
        this.owner = owner;
        this.ordersHistory = new ArrayList<>();
    }

    @Override
    public UUID id() throws RemoteException {
        return id;
    }

    @Override
    public User owner() throws RemoteException {
        return owner;
    }

    @Override
    public RemoteOptional<User> orderer() throws RemoteException {
        return RemoteOptional.ofNullable(orderer);
    }

    @Override
    public List<BikeOrder> ordersHistory() throws RemoteException {
        return List.copyOf(ordersHistory);
    }

    void setOrderer(User orderer) {
        this.orderer = orderer;
    }
}
