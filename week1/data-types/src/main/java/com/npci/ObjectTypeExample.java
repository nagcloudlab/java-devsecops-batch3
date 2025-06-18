package com.npci;


// user defined object type
class Account {
    // a.state
    int number; // 0 // Required
    double balance; // 0.0 // Optional
    // b.constructor
    //public Account() {}
    // Overloaded constructors
    public Account(int number){
        if(number < 0){
            throw new IllegalArgumentException();
        }
        this.number= number;
    }
    public Account(int number, double balance){
        if(number < 0){
            throw new IllegalArgumentException();
        }
        this.number = number;
        this.balance = balance;
    }
    // c.methods
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
    }
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }
}

public class ObjectTypeExample {
    public static void main(String[] args) {

        Account account1 = new Account(123);
        Account account2 = new Account(456, 1000.0);

        account1.deposit(1000.0);
        account2.withdraw(500.0);

    }
}
