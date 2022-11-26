package com.kamelia.gustavebikeservice.service;

import com.kamelia.ebc.common.base.NotifiableUser;
import com.kamelia.ebc.common.base.Notifier;
import com.kamelia.gustavebikeservice.RMIClient;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.io.Serializable;
import java.rmi.RemoteException;

@WebService
public class AuthService {

    @WebMethod
    public NotifiableUser login(String username, String password) throws RemoteException {
        var e = RMIClient.instance().userStorage().login(username, password);
        return e.orElseThrow().first();
    }

    @WebMethod
    public String sayHello(String name) {
        return new String("Hello " + name);
    }

    @WebMethod
    public NotifiableUser register(String username, String password) throws RemoteException {
        var e = RMIClient.instance().userStorage().save(username, password, Notifier.create());
        return e.orElseThrow();
    }

    static class Foo implements Serializable {
        public String bar;

        public Foo(String bar) {
            this.bar = bar;
        }

        public String getBar() {
            return bar;
        }
    }

}
