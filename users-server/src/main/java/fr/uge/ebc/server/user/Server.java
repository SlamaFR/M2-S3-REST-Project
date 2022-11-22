package fr.uge.ebc.server.user;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Objects;

class Server {

    private static Server INSTANCE;

    private final UserStorageImpl userStorage;

    private Server(UserStorageImpl userStorage) {
        Objects.requireNonNull(userStorage);
        this.userStorage = userStorage;
    }

    private void run(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost:1099/UserStorage", userStorage);
    }

    public static void main(String[] args) throws Exception {
        var storage = new UserStorageImpl();
        var server = new Server(storage);
        INSTANCE = server;
        server.run(args);
    }

}
