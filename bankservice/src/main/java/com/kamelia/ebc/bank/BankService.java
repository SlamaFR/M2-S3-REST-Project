package com.kamelia.ebc.bank;

import com.kamelia.ebc.bank.dto.Response;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BankService {

    /**
     * This map stores bank accounts. A given UUID represents an account, and maps to a balance in EUR (Euros).
     */
    private final static HashMap<UUID, Integer> BANK_ACCOUNTS = new HashMap<>();

    /**
     * Checks if the given bank account has at least the given amount of money.
     */
    public Response checkBalance(String bankAccount, int amount) {
        var uuid = UUID.fromString(bankAccount);
        int accountBalance = Objects.requireNonNull(BANK_ACCOUNTS.computeIfAbsent(uuid, new Function<UUID, Integer>() {
            @Override
            public Integer apply(UUID k) {
                return 0;
            }
        }));
        if (accountBalance < amount) {
            return new Response("INSUFFICIENT_BALANCE", accountBalance);
        } else {
            return new Response("OK", accountBalance);
        }
    }

    /**
     * Credits the given amount of money from the given bank account.
     */
    public Response credit(String bankAccount, int amount) {
        var uuid = UUID.fromString(bankAccount);
        var newBalance = BANK_ACCOUNTS.compute(uuid, new BiFunction<UUID, Integer, Integer>() {
            @Override
            public Integer apply(UUID k, Integer v) {
                return (v == null ? 0 : v) + amount;
            }
        });
        return new Response("OK", newBalance);
    }

    /**
     * Debits the given amount of money from the given bank account.
     */
    public Response debit(String bankAccount, int amount) {
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
        return new Response("OK", newBalance);
    }
}
