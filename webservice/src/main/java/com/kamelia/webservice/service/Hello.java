package com.kamelia.webservice.service;

import com.kamelia.webservice.Foo;
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

    public Foo sayFooe(String test) {
        return new Foo(test, 34);
    }

    public Bike bigTest() {
        var user = new User(UUID.randomUUID().toString(), "toto", new Bike[0]);

        var comment = new Comment("C'était NAZE", 0);
        var returnState = new ReturnState("fine", "LOST");
        var order = new Order(Instant.now().toEpochMilli(), "toto", comment, returnState);

        var bike = new Bike(UUID.randomUUID().toString(), user.getUsername(), new Order[]{order}, "AVAILABLE", 100);

        return bike;
    }
}
