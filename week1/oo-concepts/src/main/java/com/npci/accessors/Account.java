package com.npci.accessors;

public class Account {

    private int number;
    private double balance;

    // Constructor
    public Account(int number){
        if(number < 0){throw  new IllegalArgumentException("invalid account number");}
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setBalance(double balance) {
        if(balance < 0){throw new IllegalArgumentException("invalid balance");}
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }


}
