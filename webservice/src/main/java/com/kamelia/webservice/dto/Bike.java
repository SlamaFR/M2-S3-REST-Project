package com.kamelia.webservice.dto;

public class Bike {

    private String id;
    private String owner;
    private Order[] history;
    private String availability;
    private int value;

    public Bike() {
    }

    public Bike(String id, String owner, Order[] history, String availability, int value) {
        this.id = id;
        this.owner = owner;
        this.history = history;
        this.availability = availability;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public Order[] getHistory() {
        return history;
    }

    public String getAvailability() {
        return availability;
    }

    public int getValue() {
        return value;
    }
}
