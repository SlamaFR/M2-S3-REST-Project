package com.kamelia.ebc.webserver.service;

import com.kamelia.ebc.common.base.Bike;
import com.kamelia.ebc.common.base.BikeOrder;
import com.kamelia.ebc.common.base.BikeState;
import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.ReturnState;
import com.kamelia.ebc.common.util.NotFoundException;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class BikeService {

    private final BikeStorage bikeStorage;

    public BikeService(BikeStorage bikeStorage) {
        this.bikeStorage = bikeStorage;
    }

    public Set<Bike> getAllBikes() throws RemoteException {
        return bikeStorage.allBikes().orElseThrow();
    }

    public Set<Bike> getUserOrderedBikes(UUID sessionId) throws RemoteException {
        Objects.requireNonNull(sessionId);
        return bikeStorage.userOrderedBikes(sessionId).orElseThrow();
    }

    public BikeState orderBike(UUID sessionId, UUID bikeId) throws RemoteException {
        Objects.requireNonNull(sessionId);
        Objects.requireNonNull(bikeId);
        return bikeStorage.orderBike(bikeId, sessionId).orElseThrow();
    }

    public void returnBike(UUID sessionId, UUID bikeId, String comment, ReturnState state) throws RemoteException {
        Objects.requireNonNull(sessionId);
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(comment);
        Objects.requireNonNull(state);
        var optionalBike = bikeStorage.findById(bikeId);
        if (optionalBike.isEmpty()) {
            throw new NotFoundException("Bike not found");
        }
        var bike = optionalBike.get();
        bikeStorage.returnBike(new BikeOrder(bike, Instant.now(), comment, state), sessionId)
            .orElseThrow();
    }

    public Bike addBike(UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        return bikeStorage.addOwnedBike(sessionToken).orElseThrow();
    }

    public void removeBike(UUID sessionToken, UUID bikeId) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        Objects.requireNonNull(bikeId);
        bikeStorage.removeOwnedBike(bikeId, sessionToken).orElseThrow();
    }

}
