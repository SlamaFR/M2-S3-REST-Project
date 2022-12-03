package com.kamelia.ebc.webserver.dto;

import com.kamelia.ebc.common.base.Bike;
import com.kamelia.ebc.common.base.ReturnState;
import com.kamelia.ebc.common.util.Pair;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record BikeDTO(
    UUID bikeId,
    String ownerName,
    List<OrderDTO> history,
    String availability,
    int value
) {

    public BikeDTO {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(ownerName);
        Objects.requireNonNull(history);
    }

    public static BikeDTO from(Bike bike) {
        Objects.requireNonNull(bike);
        try {
            var history = bike.ordersHistory().stream()
                .map(Pair::second)
                .sorted((a, b) -> b.instant().compareTo(a.instant()))
                .toList();
            var state = history.isEmpty() ? ReturnState.AS_NEW : history.get(0).state();
            return new BikeDTO(
                bike.id(),
                bike.owner().username(),
                bike.ordersHistory().stream().map(OrderDTO::from).toList(),
                bike.orderer().isPresent() ? "unavailable" : "available",
                state.value()
            );
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
