package com.kamelia.ebc.webserver.service;

import com.kamelia.ebc.bank.BankService;
import com.kamelia.ebc.common.util.Pair;
import com.kamelia.webservice.service.GustaveBikeService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.UUID;

@Service
public class ShoppingService {

    private final GustaveBikeService gustaveBikeService;
    private final BankService bankService;

    public ShoppingService(
        GustaveBikeService gustaveBikeService,
        BankService bankService
    ) {
        this.gustaveBikeService = gustaveBikeService;
        this.bankService = bankService;
    }

    public Pair<String, Integer> buyBike(UUID userId, String currency, UUID bikeId) throws RemoteException {
        var purchaseResponse = gustaveBikeService.buyBike(userId.toString(), bikeId.toString(), currency);
        return new Pair<>(purchaseResponse.getMessage(), purchaseResponse.getStatus());
    }

    public Double getBalance(UUID bankAccountId, String currency) throws RemoteException {
        return bankService.getBalance(bankAccountId.toString(), currency);
    }
}
