package com.kamelia.ebc.server;

import com.kamelia.ebc.common.Bike;
import com.kamelia.ebc.common.BikeStorage;
import com.kamelia.ebc.common.Comment;
import com.kamelia.ebc.common.ReturnState;
import com.kamelia.ebc.common.User;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

class UserImpl extends AbstractUser implements User {

    private final String hashedPassword;
    private final BikeStorage storage;

    public UserImpl(String username, Kind kind, String hashedPassword) throws RemoteException {
        super(username, kind);
        Objects.requireNonNull(hashedPassword);
        this.hashedPassword = hashedPassword;
        this.storage = Server.instance().bikeStorage();
    }



    @Override
    public void orderBike(UUID bikeId) throws RemoteException {
        Objects.requireNonNull(bikeId);
        var response = storage.order(bikeId, this);
        if (response.state() != ReturnState.OK) {
            throw new RemoteException(response.message());
        }
        // TODO get the bike
    }

    @Override
    public void returnBike(UUID bikeId, Comment comment, ReturnState state) throws RemoteException {

    }

    String hashedPassword() {
        return hashedPassword;
    }

}
