package com.kamelia.ebc.webserver.service;

import com.kamelia.ebc.common.base.RemoteOptional;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.base.UserStorage;
import com.kamelia.ebc.common.util.UnauthorizedException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
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

    public void logout(UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        userStorage.logout(sessionToken);
    }

    public RemoteOptional<User> findByUsername(String username) throws RemoteException {
        Objects.requireNonNull(username);
        return userStorage.findByUsername(username);
    }
}
