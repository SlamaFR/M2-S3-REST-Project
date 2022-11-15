package com.kamelia.ebc.server;

import com.kamelia.ebc.common.UserDataHolder;

import java.rmi.RemoteException;

public class UserDataHolderImpl extends AbstractUser implements UserDataHolder {

    public UserDataHolderImpl(String username, Kind kind) throws RemoteException {
        super(username, kind);
    }

}
