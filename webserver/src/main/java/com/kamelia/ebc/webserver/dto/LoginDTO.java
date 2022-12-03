package com.kamelia.ebc.webserver.dto;

import java.util.Objects;
import java.util.UUID;

public record LoginDTO(
    UUID sessionToken
) {

    public LoginDTO {
        Objects.requireNonNull(sessionToken);
    }

}
