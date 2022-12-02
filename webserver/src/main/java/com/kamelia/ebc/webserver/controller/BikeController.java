package com.kamelia.ebc.webserver.controller;

import com.kamelia.ebc.webserver.dto.BikeDTO;
import com.kamelia.ebc.webserver.service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.RemoteException;
import java.util.Set;

@Controller
@RequestMapping("/api/bike")
public class BikeController {

    // can you please do the thing
    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<BikeDTO>> getAllBikes() throws RemoteException {
        return ResponseEntity.ok(bikeService.getAllBikes());
    }


}
