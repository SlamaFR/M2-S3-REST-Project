package com.kamelia.ebc.webserver.service;

import com.kamelia.ebc.common.base.UserStorage;
import com.kamelia.ebc.common.util.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserStorage userStorage;

    public AuthenticationService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean register(String username, String password) throws RemoteException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        return userStorage.save(username, password);
    }

    public UUID login(String username, String password) throws RemoteException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        return userStorage.login(username, password)
            .orElseThrow((state, message) -> new UnauthorizedException(message));
    }

    public void logout(UUID sessionId) throws RemoteException {
        Objects.requireNonNull(sessionId);
        userStorage.logout(sessionId);
    }

}
