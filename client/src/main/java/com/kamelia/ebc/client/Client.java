package com.kamelia.ebc.client;

import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.Response;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.base.UserStorage;

import java.rmi.Naming;
import java.util.Objects;

class Client {

    private final UserStorage userStorage;
    private final BikeStorage bikeStorage;

    private Client(UserStorage userStorage, BikeStorage bikeStorage) {
        Objects.requireNonNull(userStorage);
        Objects.requireNonNull(bikeStorage);
        this.userStorage = userStorage;
        this.bikeStorage = bikeStorage;
    }

    private void run(String[] args) throws Exception {
        var pimmy = userStorage.save("Pimmy", "p@ssw0rd");
        if (pimmy.state() != Response.State.OK) {
            throw new IllegalStateException("Could not create user:" + pimmy.message());
        }

        var user = pimmy.body().get();

        System.out.println(user.username());
    }

    public static void main(String[] args) {
        try {
            var userStorage = (UserStorage) Naming.lookup("rmi://localhost:1099/UserStorage");
            var bikeStorage = (BikeStorage) Naming.lookup("rmi://localhost:1100/BikeStorage");
            var client = new Client(userStorage, bikeStorage);
            client.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
