package com.kamelia.ebc.webserver.dto;

import java.util.Objects;

public record ReturnDTO(
    String comment,
    String returnState
) {

    public ReturnDTO {
        Objects.requireNonNull(comment);
        Objects.requireNonNull(returnState);
    }
}
