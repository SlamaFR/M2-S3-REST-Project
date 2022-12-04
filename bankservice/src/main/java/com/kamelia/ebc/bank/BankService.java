package com.kamelia.ebc.bank;

import FxtopAPI.FxtopServicesLocator;
import FxtopAPI.FxtopServicesPortType;
import com.kamelia.ebc.bank.dto.Response;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BankService {

    public static final double DEFAULT_BALANCE = 1000.0;
    public static final String DEFAULT_CURRENCY = "USD";

    /**
     * This map stores bank accounts. A given UUID represents an account, and maps to a balance in USD (Dollars).
     */
    private final static HashMap<UUID, Double> BANK_ACCOUNTS = new HashMap<>();
    //private final static FxtopServicesPortType CURRENCY_CONVERTER;
//
    //static {
    //    try {
    //        CURRENCY_CONVERTER = new FxtopServicesLocator().getFxtopServicesPort();
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //        throw new RuntimeException(e);
    //    }
    //}

    /**
     * Checks if the given bank account has at least the given amount of money.
     */
    public Response checkBalance(String bankAccount, double amount) {
        var uuid = UUID.fromString(bankAccount);
        double accountBalance = BANK_ACCOUNTS.computeIfAbsent(uuid, new Function<UUID, Double>() {
            @Override
            public Double apply(UUID k) {
                return DEFAULT_BALANCE;
            }
        });
        if (accountBalance < amount) {
            return new Response("INSUFFICIENT_BALANCE", accountBalance);
        } else {
            return new Response("OK", accountBalance);
        }
    }

    private Double convert(Double amount, String sourceCurr, String destCurr) throws RemoteException {
        //var convertedBalance = CURRENCY_CONVERTER.convert(amount.toString(), sourceCurr, destCurr, "", "", "");
        //return Double.valueOf(convertedBalance.getResultAmount());
        return amount;
    }

    /**
     * Returns the balance of the given bank account.
     */
    public Double getBalance(String bankAccount, String currency) throws RemoteException {
        var uuid = UUID.fromString(bankAccount);
        var balance = BANK_ACCOUNTS.computeIfAbsent(uuid, new Function<UUID, Double>() {
            @Override
            public Double apply(UUID k) {
                return DEFAULT_BALANCE;
            }
        });
        return convert(balance, DEFAULT_CURRENCY, currency);
    }

    /**
     * Credits the given amount of money from the given bank account.
     */
    public Response credit(String bankAccount, String currency, double amount) throws RemoteException {
        var uuid = UUID.fromString(bankAccount);
        var newBalance = BANK_ACCOUNTS.compute(uuid, new BiFunction<UUID, Integer, Integer>() {
            @Override
            public Integer apply(UUID k, Integer v) {
                return (v == null ? DEFAULT_BALANCE : v) + amount;
            }
        });
        return new Response("OK", newBalance);
    }

    /**
     * Debits the given amount of money from the given bank account.
     */
    public Response debit(String bankAccount, String currency, double amount) throws RemoteException {
        var uuid = UUID.fromString(bankAccount);
        var check = checkBalance(bankAccount, amount);
        if (!"OK".equals(check.getState())) {
            return new Response("INSUFFICIENT_BALANCE", check.getBalance());
        }
        var newBalance = BANK_ACCOUNTS.compute(uuid, new BiFunction<UUID, Integer, Integer>() {
            @Override
            public Integer apply(UUID k, Integer v) {
                return (v == null ? 0 : v) - amount;
            }
        });
        return new Response("OK", convert(newBalance, DEFAULT_CURRENCY, currency));
    }
}
