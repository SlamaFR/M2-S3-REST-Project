package com.kamelia.ebc.client;

import com.kamelia.ebc.common.User;
import com.kamelia.ebc.common.UserStorage;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) {
        try {
            var userStorage = (UserStorage) Naming.lookup("rmi://localhost:1099/UserStorage");

            var pimmy = userStorage.save("Pimmy", User.Kind.STUDENT, "p@ssw0rd");

            System.out.println("User: " + pimmy.id());

            var pimmy2 = userStorage.findById(pimmy.id());

            System.out.println("User: " + pimmy2.isPresent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
