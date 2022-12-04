package com.kamelia.ebc.webserver.controller;

import com.kamelia.ebc.common.util.Pair;
import com.kamelia.ebc.webserver.dto.UserDTO;
import com.kamelia.ebc.webserver.service.ShoppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.RemoteException;
import java.util.UUID;

@Controller
@RequestMapping("/api/buy")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/{bikeId}")
    public ResponseEntity<String> buyBike(
        @RequestBody UserDTO user,
        @PathVariable("bikeId") UUID bikeId
    ) throws RemoteException {
        var pair = shoppingService.buyBike(user.id(), bikeId);
        return ResponseEntity.status(pair.second()).body(pair.first());
    }
}
