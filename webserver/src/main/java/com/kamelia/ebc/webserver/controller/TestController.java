package com.kamelia.ebc.webserver.controller;

import com.kamelia.webservice.dto.Bike;
import com.kamelia.webservice.service.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;

@Controller
public class TestController {

    private final Hello hello;

    public TestController(Hello hello) {
        this.hello = hello;
    }

    @GetMapping("/rthrrhrthr")
    public ResponseEntity<Bike> test() throws RemoteException {
        return ResponseEntity.ok(hello.bigTest());
    }

}
