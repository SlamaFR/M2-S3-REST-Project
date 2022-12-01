package com.kamelia.ebc.webserver.config;

import com.kamelia.ebc.common.base.BikeStorage;
import com.kamelia.ebc.common.base.UserStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.rmi.Naming;

@Configuration
public class RMIConfiguration {

    @Bean
    public BikeStorage bikeStorage() {
        try {
            return (BikeStorage) Naming.lookup("rmi://localhost:1100/BikeStorage");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public UserStorage userStorage() {
        try {
            return (UserStorage) Naming.lookup("rmi://localhost:1099/UserStorage");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
