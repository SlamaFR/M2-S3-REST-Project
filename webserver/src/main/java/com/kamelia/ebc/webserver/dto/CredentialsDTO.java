package com.kamelia.ebc.webserver.dto;

import java.util.Objects;

public record CredentialsDTO(
    String username,
    String password
) {

    public CredentialsDTO {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
    }
}
