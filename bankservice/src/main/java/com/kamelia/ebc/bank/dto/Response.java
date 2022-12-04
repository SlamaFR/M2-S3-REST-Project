package com.kamelia.ebc.bank.dto;

public class Response {

    private String state;
    private int balance;

    public Response() {}

    public Response(String state, int balance) {
        this.state = state;
        this.balance = balance;
    }

    //public static Response ok(int balance) {
    //    return new Response("OK", balance);
    //}
//
    //public static Response insufficientBalance(int balance) {
    //    return new Response("INSUFFICIENT_BALANCE", balance);
    //}

    public String getState() {
        return state;
    }

    public int getBalance() {
        return balance;
    }
}
