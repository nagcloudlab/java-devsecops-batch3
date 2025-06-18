package com.npci.bank;

public class Application {

    public static void main(String[] args) {

        TransactionProcessor processor = new TransactionProcessor();
        processor.processTransaction(new DebitTransaction());
        processor.processTransaction(new TransferTransaction());
        //processor.processTransaction(new CreditTransaction());

    }

}
