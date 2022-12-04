package com.kamelia.webservice.service;

import com.kamelia.webservice.dto.Bike;
import com.kamelia.webservice.dto.Comment;
import com.kamelia.webservice.dto.Order;
import com.kamelia.webservice.dto.ReturnState;
import com.kamelia.webservice.dto.User;

import java.time.Instant;
import java.util.UUID;

public class Hello {

    public String sayHello(String name) {
        return "Hello, " + name;
    }

    public Bike bigTest() {
        User user = new User(UUID.randomUUID().toString(), "toto", new Bike[0]);

        Comment comment = new Comment("C'était NAZE", 0);
        ReturnState returnState = new ReturnState("fine", "LOST");
        Order order = new Order(Instant.now().toEpochMilli(), "toto", comment, returnState);

        Bike bike = new Bike(UUID.randomUUID().toString(), user.getUsername(), new Order[]{order}, "AVAILABLE", 100);

        return bike;
    }
}
