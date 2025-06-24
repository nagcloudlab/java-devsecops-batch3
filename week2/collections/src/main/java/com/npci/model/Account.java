package com.npci.model;

import java.util.Objects;

public class Account extends Object implements Comparable<Account> {

    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public boolean equals(Object o) {
        if (!(o instanceof Account account)) return false;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(accountHolderName, account.accountHolderName);
    }

    public int hashCode() {
        return Objects.hash(accountNumber, accountHolderName);
    }

    public int compareTo(Account o) {
        return this.accountNumber.compareTo(o.accountNumber);
    }

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
