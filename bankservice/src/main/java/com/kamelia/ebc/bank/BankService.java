package com.kamelia.ebc.bank;

import FxtopAPI.ConvertResult;
import FxtopAPI.FxtopServicesLocator;
import FxtopAPI.FxtopServicesPortType;
import com.kamelia.ebc.bank.dto.Response;

import javax.xml.rpc.ServiceException;
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
    private final static FxtopServicesPortType CURRENCY_CONVERTER;

    static {
        try {
            CURRENCY_CONVERTER = new FxtopServicesLocator().getFxtopServicesPort();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the given bank account has at least the given amount of money.
     */
    public Response checkBalance(String bankAccount, double amount) {
        UUID uuid = UUID.fromString(bankAccount);
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
        ConvertResult convertedBalance = CURRENCY_CONVERTER.convert(amount.toString(), sourceCurr, destCurr, "", "", "");
        return Double.valueOf(convertedBalance.getResultAmount());
        //return amount;
    }

    /**
     * Returns the balance of the given bank account.
     */
    public Double getBalance(String bankAccount, String currency) throws RemoteException {
        UUID uuid = UUID.fromString(bankAccount);
        Double balance = BANK_ACCOUNTS.computeIfAbsent(uuid, new Function<UUID, Double>() {
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
        UUID uuid = UUID.fromString(bankAccount);

        Double newBalance = BANK_ACCOUNTS.compute(uuid, new BiFunction<UUID, Double, Double>() {
            @Override
            public Double apply(UUID k, Double v) {
                return (v == null ? DEFAULT_BALANCE : v) + amount;
            }
        });
        return new Response("OK", convert(newBalance, DEFAULT_CURRENCY, currency));
    }

    /**
     * Debits the given amount of money from the given bank account.
     */
    public Response debit(String bankAccount, String currency, double amount) throws RemoteException {
        UUID uuid = UUID.fromString(bankAccount);
        Response check = checkBalance(bankAccount, amount);
        if (!"OK".equals(check.getState())) {
            return new Response("INSUFFICIENT_BALANCE", check.getBalance());
        }
        Double newBalance = BANK_ACCOUNTS.compute(uuid, new BiFunction<UUID, Double, Double>() {
            @Override
            public Double apply(UUID k, Double v) {
                return (v == null ? .0 : v) - amount;
            }
        });
        return new Response("OK", convert(newBalance, DEFAULT_CURRENCY, currency));
    }
}
