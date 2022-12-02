package com.kamelia.ebc.webserver.dto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record BikeDTO(
    UUID bikeId,
    String ownerName,
    List<OrderDTO> history,
    boolean isAvailable,
    int value
) {

    public BikeDTO {
        Objects.requireNonNull(bikeId);
        Objects.requireNonNull(ownerName);
        Objects.requireNonNull(history);
    }
}
