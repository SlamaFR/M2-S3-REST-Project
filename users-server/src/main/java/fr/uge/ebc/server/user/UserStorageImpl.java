package fr.uge.ebc.server.user;

import com.kamelia.ebc.common.base.RemoteOptional;
import com.kamelia.ebc.common.base.Response;
import com.kamelia.ebc.common.base.User;
import com.kamelia.ebc.common.base.UserStorage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class UserStorageImpl extends UnicastRemoteObject implements UserStorage {

    private final HashMap<UUID, UserImpl> idToUser;
    private final HashMap<String, UserImpl> nameToUser;
    private final HashMap<UUID, User> sessionToUser;
    private final HashMap<UUID, UUID> userIdToSession;
    private final HashMap<UUID, List<String>> userIdToNotifications;

    public UserStorageImpl() throws RemoteException {
        this.idToUser = new HashMap<>();
        this.nameToUser = new HashMap<>();
        this.sessionToUser = new HashMap<>();
        this.userIdToSession = new HashMap<>();
        this.userIdToNotifications = new HashMap<>();
    }

    @Override
    public RemoteOptional<User> findById(UUID id) throws RemoteException {
        Objects.requireNonNull(id);
        return RemoteOptional.ofNullable(idToUser.get(id));
    }

    @Override
    public RemoteOptional<User> findByUsername(String username) throws RemoteException {
        Objects.requireNonNull(username);
        return RemoteOptional.ofNullable(nameToUser.get(username));
    }

    @Override
    public boolean save(String username, String password) throws RemoteException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        if (nameToUser.containsKey(username)) {
            return false;
        }
        var hashedPassword = hashPassword(password);
        var user = new UserImpl(UUID.randomUUID(), username, hashedPassword);

        idToUser.put(user.id(), user);
        nameToUser.put(user.username(), user);
        return true;
    }

    @Override
    public Response<UUID> login(String username, String password) throws RemoteException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        var hashedPassword = hashPassword(password);
        var user = nameToUser.get(username);

        if (user == null || !user.hashedPassword().equals(hashedPassword)) {
            return Response.badRequest("Invalid username or password");
        }

        var sessionId = UUID.randomUUID();
        try {
            userIdToSession.compute(user.id(), (ignored, session) -> {
                if (session != null) {
                    throw new IllegalStateException("User already logged in");
                }
                sessionToUser.put(sessionId, user);
                return sessionId;
            });
        } catch (IllegalStateException e) {
            return Response.badRequest("Already logged in");
        }

        return Response.ok(sessionId);
    }

    @Override
    public void logout(UUID sessionId) throws RemoteException {
        Objects.requireNonNull(sessionId);
        sessionToUser.remove(sessionId);
    }

    @Override
    public RemoteOptional<UUID> isAuthenticated(UUID sessionId) throws RemoteException {
        Objects.requireNonNull(sessionId);
        var user = sessionToUser.get(sessionId);
        if (user == null) {
            return RemoteOptional.empty();
        }
        return RemoteOptional.ofNullable(userIdToSession.get(user.id()));
    }

    @Override
    public Response<List<String>> notifications(UUID sessionToken) throws RemoteException {
        Objects.requireNonNull(sessionToken);
        var user = sessionToUser.get(sessionToken);
        if (user == null) {
            return Response.unauthorized("Invalid session token");
        }
        return Response.ok(userIdToNotifications.get(sessionToken));
    }

    @Override
    public void addNotification(UUID userId, String notification) throws RemoteException {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(notification);
        if (!idToUser.containsKey(userId)) {
            return;
        }
        userIdToNotifications.computeIfAbsent(userId, ignored -> new ArrayList<>()).add(notification);
    }

    private static String hashPassword(String password) {
        return Hasher.instance().hash(password);
    }
}
