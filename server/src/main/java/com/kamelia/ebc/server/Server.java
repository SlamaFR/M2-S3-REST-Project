package com.kamelia.ebc.server;

import com.kamelia.ebc.common.BikeStorage;
import com.kamelia.ebc.common.UserStorage;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    private static Server INSTANCE;

    private final UserStorage userStorage;
    private final BikeStorage bikeStorage;

    private Server() throws RemoteException {
        this.userStorage = new UserStorageImpl();
        this.bikeStorage = new BikeStorageImpl();
    }

    private void run(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost:1099/BikeStorage", bikeStorage);
        Naming.rebind("rmi://localhost:1099/UserStorage", userStorage);
    }

    public static void main(String[] args) throws Exception {
        var server = new Server();
        INSTANCE = server;
        server.run(args);
    }

    public static Server instance() {
        return INSTANCE;
    }

    public UserStorage userStorage() {
        return userStorage;
    }

    public BikeStorage bikeStorage() {
        return bikeStorage;
    }
}
