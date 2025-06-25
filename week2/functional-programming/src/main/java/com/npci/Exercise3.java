package com.npci;

import com.npci.model.Trader;
import com.npci.model.TraderTransaction;

import java.util.Arrays;
import java.util.List;

public class Exercise3 {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<TraderTransaction> transactions = Arrays.asList(
                new TraderTransaction(brian, 2011, 300),
                new TraderTransaction(raoul, 2012, 1000),
                new TraderTransaction(raoul, 2011, 400),
                new TraderTransaction(mario, 2012, 710),
                new TraderTransaction(mario, 2012, 700),
                new TraderTransaction(alan, 2012, 950)
        );
        //----------------------------------------------------------
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        // Query 2: What are all the unique cities where the traders work?
        // Query 3: Find all traders from Cambridge and sort them by name.
        // Query 5: Are there any trader based in Milan?
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        //----------------------------------------------------------
    }
}
