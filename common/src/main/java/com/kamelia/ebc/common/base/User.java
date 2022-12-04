package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface User extends Remote {

    String username() throws RemoteException;

    UUID id() throws RemoteException;

}
