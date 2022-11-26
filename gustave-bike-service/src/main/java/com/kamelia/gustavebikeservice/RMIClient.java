package com.kamelia.gustavebikeservice;

import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.UserStorage;

import java.rmi.Naming;

public class RMIClient {

    private final UserStorage userStorage;
    private final BikeStorage bikeStorage;

    private RMIClient(UserStorage userStorage, BikeStorage bikeStorage) {
        this.userStorage = userStorage;
        this.bikeStorage = bikeStorage;
    }

    public static RMIClient create() {
        try {
            var userStorage = (UserStorage) Naming.lookup("rmi://localhost:1099/UserStorage");
            var bikeStorage = (BikeStorage) Naming.lookup("rmi://localhost:1100/BikeStorage");
            return new RMIClient(userStorage, bikeStorage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserStorage userStorage() {
        return userStorage;
    }

    public BikeStorage bikeStorage() {
        return bikeStorage;
    }
}
