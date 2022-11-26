package com.kamelia.ebc.common.base;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

final class NotifierImpl extends UnicastRemoteObject implements Notifier {


    public NotifierImpl() throws RemoteException {
    }

    @Override
    public void notifyOnReturnedBike(Bike bike) throws RemoteException {
        Objects.requireNonNull(bike);
        System.out.println(
            "Hello, the bike " +
                bike.id() +
                " is now free and will be ordered for you"
        );
    }

}
