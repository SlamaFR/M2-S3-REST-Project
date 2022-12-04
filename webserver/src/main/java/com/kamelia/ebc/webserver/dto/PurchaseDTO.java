package com.kamelia.ebc.webserver.dto;

import java.util.Objects;
import java.util.UUID;

public record PurchaseDTO(
    UUID userId,
    String currency
) {

    public PurchaseDTO {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(currency);
    }
}
