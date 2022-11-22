package com.kamelia.ebc.client;

import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.Response;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.base.UserStorage;

import java.rmi.Naming;
import java.util.Objects;
import java.util.Optional;

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
        var pimmy = userStorage
            .save("Pimmy", "p@ssw0rd")
            .orElseThrow();

        var token = userStorage
            .login("Pimmy", "p@ssw0rd")
            .orElseThrow()
            .second();

        var bike = bikeStorage
            .addOwnedBike(pimmy, token)
            .orElseThrow();

        System.out.println(bike.id());
        System.out.println(bike.owner().id());
        System.out.println(bike.owner().username());
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
