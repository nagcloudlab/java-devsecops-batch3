package com.npci;


// user defined object type
class Account {
    // a.variables
    // a. class  variables ( aka static variables )
    static String bankName;
    // b. object variables ( aka instance variables )
    int number; // 0 // Required
    double balance; // 0.0 // Optional

    // b.constructor
    //public Account() {}
    // Overloaded constructors
    Account(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        this.number = number;
    }

    Account(int number, double balance) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        this.number = number;
        this.balance = balance;
    }

    // c.methods
    // a. class methods ( aka static methods )
    public static String getBankName() {
        return bankName;
    }

    // b. object methods ( aka instance methods )
    void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
    }

    void withdraw(double amount) {
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

        Account.bankName = "National Payments Corporation of India";
        System.out.println("Bank Name: " + Account.getBankName());


        Account account1 = new Account(123);
        System.out.println("Account Number: " + account1.number);
        System.out.println("Account Balance: " + account1.balance);
        Account account2 = new Account(456, 1000.0);
        System.out.println("Account Number: " + account2.number);
        System.out.println("Account Balance: " + account2.balance);

        account1.deposit(1000.0);
        account2.withdraw(500.0);

    }
}
