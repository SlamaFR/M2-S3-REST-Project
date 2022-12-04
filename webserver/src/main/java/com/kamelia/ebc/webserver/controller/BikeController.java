package com.kamelia.ebc.webserver.controller;

import com.kamelia.ebc.common.base.BikeState;
import com.kamelia.ebc.common.base.ReturnState;
import com.kamelia.ebc.webserver.dto.BikeDTO;
import com.kamelia.ebc.webserver.dto.OrderBikesDTO;
import com.kamelia.ebc.webserver.dto.ReturnDTO;
import com.kamelia.ebc.webserver.service.BikeService;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/bike")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<BikeDTO>> getAllBikes() throws RemoteException {
        return ResponseEntity.ok(bikeService.getAllBikes()
            .stream()
            .map(BikeDTO::from)
            .collect(Collectors.toSet()));
    }

    @GetMapping("/{bikeId}")
    public ResponseEntity<BikeDTO> getBike(
        @PathVariable UUID bikeId
    ) throws RemoteException {
        return ResponseEntity.ok(BikeDTO.from(bikeService.getBike(bikeId)));
    }

    @GetMapping("/rented")
    public ResponseEntity<Set<BikeDTO>> getRentedBikes(
        @RequestHeader(name = "Session-Token") UUID sessionToken
    ) throws RemoteException {
        return ResponseEntity.ok(bikeService.getUserOrderedBikes(sessionToken)
            .stream()
            .map(BikeDTO::from)
            .collect(Collectors.toSet()));
    }

    @PostMapping("/return/{bikeId}")
    public ResponseEntity<Void> returnBike(
        @PathVariable("bikeId") UUID bikeId,
        @RequestHeader(name = "Session-Token") UUID sessionToken,
        @RequestBody ReturnDTO dto
    ) throws RemoteException {
        bikeService.returnBike(sessionToken, bikeId, dto.comment(), ReturnState.fromString(dto.returnState()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/order")
    public ResponseEntity<List<BikeState>> orderBikes(
        @RequestBody OrderBikesDTO dto,
        @RequestHeader(name = "Session-Token") UUID sessionToken
    ) throws RemoteException {
        return ResponseEntity.ok(bikeService.orderBikes(sessionToken, dto.bikesIds()));
    }

    @PostMapping("/add")
    public ResponseEntity<BikeDTO> create(
        @RequestHeader(name = "Session-Token") UUID sessionToken
    ) throws RemoteException {
        var bike = bikeService.addBike(sessionToken);
        return ResponseEntity.ok(BikeDTO.from(bike));
    }

    @DeleteMapping("/delete/{bikeId}")
    public ResponseEntity<Void> delete(
        @RequestHeader(name = "Session-Token") UUID sessionToken,
        @PathVariable("bikeId") UUID bikeId
    ) throws RemoteException {
        bikeService.removeBike(sessionToken, bikeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<String>> notifications(
        @RequestHeader(name = "Session-Token") UUID sessionToken
    ) throws RemoteException{
        return ResponseEntity.ok(bikeService.notifications(sessionToken));
    }


}
