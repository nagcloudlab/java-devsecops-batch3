package com.npci;

import com.npci.model.Account;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExampleApplication {
    public static void main(String[] args) {

//        createMillionsAccounts(new TreeSet<>()); // red-black tree ( b-tree)  -> sorted
//        createMillionsAccounts(new HashSet<>(100,0.75F)); // hash table
//        createMillionsAccounts(new LinkedHashSet<>()); // hash table with linked list


        //Set<Account> accounts = new TreeSet<>(); // can hold only comparable objects else providing comparator is mandatory
        //accounts.add(new Account("acc_2", "name_2", 2000.00));
        //accounts.add(new Account("acc_1", "name_1", 1000.00));
        //accounts.add(new Account("acc_3", "name_3", 3000.00));
        //accounts.add(new Account("acc_2", "name_2", 200.00));

        //Set<Account> accounts = new HashSet<>(); // hash table, no order, no duplicates , objects must implement equals() and hashCode() methods
        Set<Account> accounts = new LinkedHashSet<>(); // maintains insertion order, no duplicates , objects must implement equals() and hashCode() methods

        Account account1 = new Account("acc_1", "name_1", 1000.00);
//        System.out.println(account1.hashCode());
        Account account2 = new Account("acc_1", "name_1", 1000.00);
//        System.out.println(account2.hashCode());

//        System.out.println("account1.equals(account2) = " + account1.equals(account2));


        accounts.add(account1);
        accounts.add(account2); // will not be added as it is duplicate
        accounts.add(new Account("acc_4", "name_4", 4000.00));
        accounts.add(new Account("acc_2", "name_2", 2000.00));
        accounts.add(new Account("acc_3", "name_3", 3000.00));
        accounts.add(new Account("acc_2", "name_2", 200.00)); // will not be added as it is duplicate


        displayAccounts(accounts);


    }

    private static void displayAccounts(Set<Account> accounts) {
        System.out.println("Accounts in " + accounts.getClass().getSimpleName() + ":");
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private static void createMillionsAccounts(java.util.Set<Account> set) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            set.add(new Account("acc_" + i, "name_" + i, 1000.00));
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken to create 1 million accounts in " + set.getClass().getSimpleName() + ": " + (end - start) + " ms");
    }
}
