package com.kamelia.ebc.webserver.dto;

import com.kamelia.ebc.common.base.User;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.UUID;

public record UserDTO(UUID id, String username) {

    public UserDTO {
        Objects.requireNonNull(id);
        Objects.requireNonNull(username);
    }

    public static UserDTO from(User user) throws RemoteException {
        return new UserDTO(
            user.id(),
            user.username()
        );
    }
}
