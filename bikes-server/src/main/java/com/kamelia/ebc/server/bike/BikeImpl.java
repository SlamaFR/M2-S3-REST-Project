package com.kamelia.ebc.server.bike;

import com.kamelia.ebc.common.base.Bike;
import com.kamelia.ebc.common.base.BikeOrder;
import com.kamelia.ebc.common.base.RemoteOptional;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.util.Pair;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class BikeImpl extends UnicastRemoteObject implements Bike {

    private final UUID id;
    private User owner;
    private final ArrayList<Pair<User, BikeOrder>> ordersHistory;
    private User orderer;
    private boolean removedFromOrders;

    BikeImpl(UUID id, User owner) throws RemoteException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(owner);

        this.id = id;
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
    public boolean removedFromOrders() throws RemoteException {
        synchronized (id) {
            return removedFromOrders;
        }
    }

    @Override
    public RemoteOptional<User> orderer() throws RemoteException {
        synchronized (id) {
            return RemoteOptional.ofNullable(orderer);
        }
    }

    @Override
    public List<Pair<User, BikeOrder>> ordersHistory() throws RemoteException {
        synchronized (id) {
            return List.copyOf(ordersHistory);
        }
    }

    void setOwner(User owner) {
        Objects.requireNonNull(owner);
        synchronized (id) {
            this.owner = owner;
        }
    }

    void setOrderer(User orderer) {
        synchronized (id) {
            this.orderer = orderer;
        }
    }

    void addOrder(User user, BikeOrder order) {
        synchronized (id) {
            Objects.requireNonNull(user);
            Objects.requireNonNull(order);
            ordersHistory.add(new Pair<>(user, order));
        }
    }

    void setRemovedFromOrders() {
        synchronized (id) {
            removedFromOrders = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        synchronized (id) {
            if (this == o) return true;
            if (!(o instanceof BikeImpl b)) return false;

            return id.equals(b.id)
                && owner.equals(b.owner)
                && ordersHistory.equals(b.ordersHistory)
                && Objects.equals(orderer, b.orderer);
        }
    }

    @Override
    public int hashCode() {
        synchronized (id) {
            return Objects.hash(id, owner, ordersHistory, orderer);
        }
    }
}
