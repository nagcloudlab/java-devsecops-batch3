package com.npci;

import com.npci.lib.TransactionLib;
import com.npci.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Application {
    public static void main(String[] args) {

        // load  1000 transactions, by api-call or from a file or database or kafka topic
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Transaction transaction = new Transaction(
                    "txn" + i,
                    "sender" + i,
                    "receiver" + i,
                    100 + i,
                    "2023-10-01T10:00:" + (i % 60) + "Z"
            );
            transactions.add(transaction);
        }

        //------------------------------------------------------------------
        // before java-8
        //------------------------------------------------------------------
        // Req-1 : filter transactions by amount < 1000.00
        class AmountLesserThan1000 implements Predicate<Transaction> {
            public boolean test(Transaction transaction) {
                return transaction.getAmount() < 1000.00;
            }
        }
        List<Transaction> output = TransactionLib.filter(transactions, new AmountLesserThan1000());
        //displayTransactions(output);

        // Req-2 : filter transactions by time > 2023-10-01T10:00:30Z
        class TimeGreaterThan20231001100030 implements Predicate<Transaction> {
            public boolean test(Transaction transaction) {
                return transaction.getTimestamp().compareTo("2023-10-01T10:00:30Z") > 0;
            }
        }
        output = TransactionLib.filter(transactions, new TimeGreaterThan20231001100030());
        //displayTransactions(output);

        //------------------------------------------------------------------
        // from java-8
        //------------------------------------------------------------------

//         Lambda expression aka function

//        Predicate<Transaction> amountLesserThan1000 =  (Transaction transaction)->{
//             return transaction.getAmount() < 1000.00;
//        };
//        Predicate<Transaction> amountLesserThan1000 =  (transaction)->{
//            return transaction.getAmount() < 1000.00;
//        };
//        Predicate<Transaction> amountLesserThan1000 =  transaction->{
//            return transaction.getAmount() < 1000.00;
//        };

//        Predicate<Transaction> amountLesserThan1000 = transaction -> transaction.getAmount() < 1000.00;

//        output = TransactionLib.filter(transactions, transaction -> transaction.getAmount() < 1000.00);
//        displayTransactions(output);
//
//        // Req-2 : filter transactions by time > 2023-10-01T10:00:30Z
//        output = TransactionLib.filter(transactions, transaction -> transaction.getTimestamp().compareTo("2023-10-01T10:00:30Z") > 0);


        // Req-3 : sort all transactions by amount in desc order
//        output = TransactionLib.sort(transactions, (t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()));
//        displayTransactions(output);


        // Req-4 : filter transactions by amount < 1000.00 and time > 2023-10-01T10:00:30Z
        Predicate<Transaction> p1 = transaction -> transaction.getAmount() < 1000.00;
        Predicate<Transaction> p2 = transaction -> transaction.getTimestamp().compareTo("2023-10-01T10:00:30Z") > 0;
        Predicate<Transaction> p3 = p1.or(p2);
        output = TransactionLib.filter(transactions, p3);
        displayTransactions(output);


    }

    private static void displayTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
