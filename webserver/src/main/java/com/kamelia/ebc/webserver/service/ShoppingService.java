package com.kamelia.ebc.webserver.service;

import com.kamelia.ebc.common.util.Pair;
import com.kamelia.webservice.service.GustaveBikeService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.UUID;

@Service
public class ShoppingService {

    private final GustaveBikeService gustaveBikeService;

    public ShoppingService(GustaveBikeService gustaveBikeService) {
        this.gustaveBikeService = gustaveBikeService;
    }

    public Pair<String, Integer> buyBike(UUID userId, UUID bikeId) throws RemoteException {
        var purchaseResponse = gustaveBikeService.buyBike(userId.toString(), bikeId.toString());
        return new Pair<>(purchaseResponse.getMessage(), purchaseResponse.getStatus());
    }
}
