package com.npci.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String accountNumber) {
        super("Account not found: " + accountNumber);
    }
}
