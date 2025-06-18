package com.npci.bank;


// Open/Closed Principle (OCP)  => polymorphic object
public class TransactionProcessor {
    void processTransaction(BankTransaction transaction) {
        transaction.process();
        transaction.getDetails();
    }
}
