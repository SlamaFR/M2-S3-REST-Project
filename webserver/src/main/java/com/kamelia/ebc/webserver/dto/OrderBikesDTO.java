package com.kamelia.ebc.webserver.dto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record OrderBikesDTO(
    List<UUID> bikesIds
) {

    public OrderBikesDTO {
        Objects.requireNonNull(bikesIds);
    }

}
