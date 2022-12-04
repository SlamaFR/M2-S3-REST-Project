/**
 * BankService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kamelia.ebc.bank;

public interface BankService extends java.rmi.Remote {
    public com.kamelia.ebc.bank.dto.Response debit(String bankAccount, String currency, double amount) throws java.rmi.RemoteException;
    public com.kamelia.ebc.bank.dto.Response credit(String bankAccount, String currency, double amount) throws java.rmi.RemoteException;
    public com.kamelia.ebc.bank.dto.Response checkBalance(String bankAccount, double amount) throws java.rmi.RemoteException;
    public double getBalance(String bankAccount, String currency) throws java.rmi.RemoteException;
}
