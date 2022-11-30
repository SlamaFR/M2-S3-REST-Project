package com.kamelia.webservice.dto;

import java.time.Instant;

public class Order {

    private long date;
    private String orderer;
    private Comment comment;
    private ReturnState state;

    public Order() {
    }

    public Order(long date, String orderer, Comment comment, ReturnState state) {
        this.date = date;
        this.orderer = orderer;
        this.comment = comment;
        this.state = state;
    }

    public long getDate() {
        return date;
    }

    public String getOrderer() {
        return orderer;
    }

    public Comment getComment() {
        return comment;
    }

    public ReturnState getState() {
        return state;
    }
}
