package com.kamelia.gustavebikeservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class HelloWebService {

    @WebMethod
    public String sayHello(String name) {
        return "Say Hello to " + name;
    }
}