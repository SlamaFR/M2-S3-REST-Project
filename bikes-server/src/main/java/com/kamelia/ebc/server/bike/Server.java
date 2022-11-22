package com.kamelia.ebc.server.bike;

import com.kamelia.ebc.common.base.UserStorage;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Objects;

class Server {

    private final BikeStorageImpl bikeStorage;
    private final UserStorage userStorage;

    private Server(BikeStorageImpl bikeStorage, UserStorage userStorage) {
        Objects.requireNonNull(bikeStorage);
        Objects.requireNonNull(userStorage);
        this.bikeStorage = bikeStorage;
        this.userStorage = userStorage;
    }

    private void run(String[] args) throws Exception {
        LocateRegistry.createRegistry(1100);
        Naming.rebind("rmi://localhost:1100/BikeStorage", bikeStorage);
    }

    public static void main(String[] args) throws Exception {
        var userStorage = (UserStorage) Naming.lookup("rmi://localhost:1099/UserStorage");
        var bikeStorage = new BikeStorageImpl(userStorage);
        var server = new Server(bikeStorage, userStorage);
        server.run(args);
    }

}