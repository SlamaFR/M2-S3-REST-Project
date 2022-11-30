package com.kamelia.webservice.dto;

public class ReturnState {

    private String details;
    private String state;

    public ReturnState() {
    }

    public ReturnState(String details, String state) {
        this.details = details;
        this.state = state;
    }

    public String getDetails() {
        return details;
    }

    public String getState() {
        return state.toString();
    }

}
