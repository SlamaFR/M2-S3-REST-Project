package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface User extends Remote {

    UUID id() throws RemoteException;

    String username() throws RemoteException;

}
