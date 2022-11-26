package com.kamelia.ebc.common.base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Notifier extends Remote {

    void notifyOnReturnedBike(Bike bike) throws RemoteException;

    static Notifier create() throws RemoteException {
        return new NotifierImpl();
    }

}
