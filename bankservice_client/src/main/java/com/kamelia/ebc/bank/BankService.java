/**
 * BankService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kamelia.ebc.bank;

public interface BankService extends java.rmi.Remote {
    public com.kamelia.ebc.bank.dto.Response debit(java.lang.String bankAccount, java.lang.String currency, double amount) throws java.rmi.RemoteException;
    public com.kamelia.ebc.bank.dto.Response credit(java.lang.String bankAccount, java.lang.String currency, double amount) throws java.rmi.RemoteException;
    public com.kamelia.ebc.bank.dto.Response checkBalance(java.lang.String bankAccount, double amount) throws java.rmi.RemoteException;
    public double getBalance(java.lang.String bankAccount, java.lang.String currency) throws java.rmi.RemoteException;
}
