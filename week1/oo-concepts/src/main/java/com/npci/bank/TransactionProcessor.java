package com.npci.bank;

public class TransactionProcessor {

    void processTransaction(BankTransaction transaction) {
        transaction.process();
        transaction.getDetails();
    }

}
