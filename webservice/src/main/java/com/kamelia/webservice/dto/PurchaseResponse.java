package com.kamelia.webservice.dto;

public class PurchaseResponse {

    private String message;
    private int status;
    
    public PurchaseResponse() {}

    public PurchaseResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
