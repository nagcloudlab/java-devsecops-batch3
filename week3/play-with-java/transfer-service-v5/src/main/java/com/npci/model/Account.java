package com.npci.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account implements Comparable<Account> {
    @Id
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;
    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;
    @Column(name = "balance", nullable = false)
    private double balance;


    public Account() {}
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


    @Override
    public int compareTo(Account o) {
        // Compare accounts by balance in descending order
        return this.getAccountNumber().compareTo(o.getAccountNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(accountHolderName, account.accountHolderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, accountHolderName);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
