package com.kamelia.ebc.server;

import com.kamelia.ebc.common.RemoteOptional;
import com.kamelia.ebc.common.Response;
import com.kamelia.ebc.common.User;
import com.kamelia.ebc.common.UserStorage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

class UserStorageImpl extends UnicastRemoteObject implements UserStorage {

    private final HashMap<UUID, UserImpl> usersById;
    private final HashMap<String, UserImpl> usersByUsername;
    private final HashMap<UUID, User> usersBySessionId;

    public UserStorageImpl() throws RemoteException {
        super();
        this.usersById = new HashMap<>();
        this.usersByUsername = new HashMap<>();
        this.usersBySessionId = new HashMap<>();
    }

    @Override
    public RemoteOptional<User> findById(UUID id) throws RemoteException {
        Objects.requireNonNull(id);
        return RemoteOptional.ofNullable(usersById.get(id));
    }

    @Override
    public RemoteOptional<User> findByUsername(String username) throws RemoteException {
        Objects.requireNonNull(username);
        return RemoteOptional.ofNullable(usersByUsername.get(username));
    }

    @Override
    public User save(String username, User.Kind kind, String password) throws RemoteException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(kind);
        Objects.requireNonNull(password);
        var hashedPassword = hashPassword(password);
        var user = new UserImpl(username, kind, hashedPassword);
        usersById.put(user.id(), user);
        usersByUsername.put(user.username(), user);
        return user;
    }

    @Override
    public Response<UUID> login(String username, String password) throws RemoteException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        var hashedPassword = hashPassword(password);
        var user = usersByUsername.get(username);
        if (user == null || !user.hashedPassword().equals(hashedPassword)) {
            return Response.badRequest();
        }
        var sessionId = UUID.randomUUID();
        usersBySessionId.put(sessionId, user);
        return Response.ok(sessionId);
    }

    @Override
    public void logout(UUID sessionId) throws RemoteException {
        Objects.requireNonNull(sessionId);
        usersBySessionId.remove(sessionId);
    }

    @Override
    public boolean isAuthorized(User user, UUID sessionId) throws RemoteException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(sessionId);
        return user.equals(usersBySessionId.get(sessionId));
    }

    private static String hashPassword(String password) {
        return password; // TODO: hash password
    }
}
