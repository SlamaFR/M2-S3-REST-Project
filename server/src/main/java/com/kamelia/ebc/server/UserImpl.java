package com.kamelia.ebc.server;

import com.kamelia.ebc.common.Bike;
import com.kamelia.ebc.common.BikeOrder;
import com.kamelia.ebc.common.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

class UserImpl extends UnicastRemoteObject implements User {

    private final UUID id;
    private final String username;
    private final String hashedPassword;
    private final Kind kind;
    private final HashSet<Bike> ownedBikes;
    private final HashSet<Bike> orderedBikes;
    private final HashSet<BikeOrder> ordersHistory;

    public UserImpl(String username, Kind kind, String hashedPassword) throws RemoteException {
        super();
        Objects.requireNonNull(username);
        Objects.requireNonNull(kind);
        Objects.requireNonNull(hashedPassword);

        this.id = UUID.randomUUID();
        this.username = username;
        this.hashedPassword = hashedPassword;
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
    public Kind kind() throws RemoteException {
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

    public String hashedPassword() {
        return hashedPassword;
    }

    void addOwnedBike(Bike bike) {
        Objects.requireNonNull(bike);
        ownedBikes.add(bike);
    }

    void addOrderedBike(Bike bike) {

        orderedBikes.add(bike);
    }

    void addOrderHistory(BikeOrder order) {
        Objects.requireNonNull(order);
        ordersHistory.add(order);
    }

    void removeOrderedBike(Bike bike) {
        Objects.requireNonNull(bike);
        orderedBikes.remove(bike);
    }

    void removeOwnedBike(Bike bike) {
        Objects.requireNonNull(bike);
        ownedBikes.remove(bike);
    }
}
