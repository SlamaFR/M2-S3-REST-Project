package com.kamelia.ebc.webserver.controller;

import com.kamelia.ebc.common.util.Pair;
import com.kamelia.ebc.webserver.dto.PurchaseDTO;
import com.kamelia.ebc.webserver.dto.UserDTO;
import com.kamelia.ebc.webserver.service.ShoppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.rmi.RemoteException;
import java.util.UUID;

@Controller
@RequestMapping("/api")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/buy/{bikeId}")
    public ResponseEntity<String> buyBike(
        @RequestBody PurchaseDTO dto,
        @PathVariable("bikeId") UUID bikeId
    ) throws RemoteException {
        var pair = shoppingService.buyBike(dto.userId(), dto.currency(), bikeId);
        return ResponseEntity.status(pair.second()).body(pair.first());
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(
        @PathVariable("userId") UUID userId,
        @RequestParam("currency") String currency
    ) throws RemoteException {
        return ResponseEntity.ok(shoppingService.getBalance(userId, currency));
    }
}
