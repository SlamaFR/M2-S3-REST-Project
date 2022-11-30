package com.kamelia.webservice.dto;

public class User {

    private String id;
    private String username;
    private Bike[] rentedBikes;

    public User() {
    }

    public User(String id, String username, Bike[] rentedBikes) {
        this.id = id;
        this.username = username;
        this.rentedBikes = rentedBikes;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Bike[] rentedBikes() {
        return rentedBikes;
    }
}
