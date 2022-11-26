package com.kamelia.ebc.client;

import com.kamelia.ebc.common.base.BikeOrder;
import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.Comment;
import com.kamelia.ebc.common.base.Notifier;
import com.kamelia.ebc.common.base.Response;
import com.kamelia.ebc.common.base.ReturnState;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.base.UserStorage;

import java.rmi.Naming;
import java.time.Instant;
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
        var notifier = Notifier.create();
        var pimmy = userStorage
            .save("Pimmy", "p@ssw0rd", notifier)
            .orElseThrow();

        var tokenPimmy = userStorage
            .login("Pimmy", "p@ssw0rd")
            .orElseThrow()
            .second();

        var bike = bikeStorage
            .addOwnedBike(pimmy.user(), tokenPimmy)
            .orElseThrow();

        var notifier2 = Notifier.create();
        var porris = userStorage
            .save("Porris", "p@ssw0rd", notifier2)
            .orElseThrow();

        var tokenPorris = userStorage
            .login("Porris", "p@ssw0rd")
            .orElseThrow()
            .second();

        bikeStorage.orderBike(bike.id(), porris, tokenPorris);

        var orderState = bikeStorage
            .orderBike(bike.id(), pimmy, tokenPimmy)
            .orElseThrow();

        System.out.println(orderState);
        System.out.println(bike.orderer().get().username());

        var porrisUser = porris.user();
        var order = new BikeOrder(
            bike,
            porrisUser,
            Instant.now(),
            new Comment("Fine", Comment.Stars.FIVE),
            new ReturnState("Ouep", ReturnState.State.AS_NEW)
        );
        var r = bikeStorage.returnBike(order, porris.user(), tokenPorris);
        System.out.println(r.state() + " " + r.message());

        Thread.sleep(3000);

        System.out.println(bike.orderer().get().username());
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
