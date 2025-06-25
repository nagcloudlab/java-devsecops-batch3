package com.npci;

import com.npci.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Filter_Intermediate_Operations {
    public static void main(String[] args) {

        // intermediate operations
        // 1. filtering
        // 2. mapping aka transformation
        // 3. sorting

        //-----------------------------------
        // 1. filtering
        //------------------------------------

        // .filter() is used to filter elements based on a condition.
        // .limit() is used to limit the number of elements in the stream.
        // .skip() is used to skip a certain number of elements in the stream.
        // .distinct() is used to remove duplicate elements from the stream.
        // .dropWhile() is used to drop elements from the stream while a condition is true.
        // .takeWhile() is used to take elements from the stream while a condition is true.


        List<Integer> numbers = List.of(2, 1, 3, 4, 5, 5, 6, 7, 8, 9, 10);

        // content based filtering : filter()
        numbers
                .stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        System.out.println();
        // count based filtering : limit() or skip()
        numbers
                .stream()
                .skip(5)
                .limit(5)
                .forEach(System.out::println);
        System.out.println();

        // removing duplicates : distinct()
        numbers
                .stream()
                .distinct() // uses equals() method to check for duplicates
                .forEach(System.out::println);


        //----------------------------------------------------------

        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Transaction transaction = new Transaction(
                    "txn" + i,
                    "sender" + i,
                    "receiver" + i,
                    100 + i,
                    "2023-10-01T10:00:" + (i % 60) + "Z"
            );
            transactions.add(transaction);
        }

        transactions
                .stream()
                .peek(t -> System.out.println("Processing transaction: " + t)) // peek() is used for debugging purposes
                //.filter(t -> t.getAmount() < 150) // check every transaction amount is less than 1000
                .takeWhile(t -> t.getAmount() < 1000) // take transactions until the amount is less than 1000
                //.dropWhile(t -> t.getAmount() < 150) // drop transactions until the amount is less than 150
                .forEach(System.out::println);
    }
}
