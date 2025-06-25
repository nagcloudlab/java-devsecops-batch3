package com.npci.lib;

import com.npci.model.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/*
author: Npci
 */
public class TransactionLib {
    // implementation of how to filter transactions efficiently ( fast, memory efficient, etc. )
    public static List<Transaction> filter(List<Transaction> input, Predicate<Transaction> predicate) {
        // filter code...
        List<Transaction> output = new ArrayList<>();
        for (Transaction t : input) {
            if (predicate.test(t)) {
                output.add(t);
            }
        }
        return output;
    }

    public static List<Transaction> sort(List<Transaction> input, Comparator<Transaction> comparator) {
        // sort code...
        List<Transaction> output = new ArrayList<>(input);
        output.sort(comparator);
        return output;
    }

}
