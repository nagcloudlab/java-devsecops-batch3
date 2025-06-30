package com.npci.exception;

public class AccountBalanceException extends RuntimeException{
    public AccountBalanceException(String accountNumber) {
        super("Insufficient balance in the account: " + accountNumber);
    }
}
