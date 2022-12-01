package com.kamelia.ebc.webserver.dto;

import java.util.Objects;
import java.util.UUID;

public record LoginDTO(
    UUID sessionId
) {

    public LoginDTO {
        Objects.requireNonNull(sessionId);
    }

}
