package com.npci;

import com.npci.model.Account;

import java.util.*;

public class ListExampleApplication {
    public static void main(String[] args) {

//        createMillionAccounts(new Vector<>()); // dynamic array
//        createMillionAccounts(new ArrayList<>()); // dynamic array
//        createMillionAccounts(new LinkedList<>()); // doubly linked list

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("acc_2", "name_2", 200.00));
        accounts.add(new Account("acc_1", "name_1", 1000.00));
        accounts.add(new Account("acc_3", "name_3", 3000.00));
        accounts.add(new Account("acc_2", "name_2", 200.00));

        //System.out.println(accounts.get(0));
        displayAccounts(accounts); // Ordered output

        // sorting
        // 2 step process
        // step-1 : compare the 2 objects
        // step-2 : swap the objects
        // sorting algorithm : bubble sort, selection sort, insertion sort, merge sort, quick sort, heap sort

        Collections.sort(accounts);  // sort it, if objects are comparable
        System.out.println("After sorting:");
        displayAccounts(accounts);

        Collections.sort(accounts, new BalanceComparator()); // else use comparator
        System.out.println("After sorting by balance:");
        displayAccounts(accounts);

        // by holder name
        Collections.sort(accounts, new HolderNameComparator()); // else use comparator
        System.out.println("After sorting by holder name:");
        displayAccounts(accounts);

    }

    private static void displayAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private static void createMillionAccounts(List<Account> accounts) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            accounts.add(new Account("acc_" + i, "name_" + i, 1000.00));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to create 1 million accounts: " + (endTime - startTime) + " ms");
    }

}


class BalanceComparator implements Comparator<Account> {
    public int compare(Account o1, Account o2) {
        if (o1.getBalance() < o2.getBalance()) {
            return -1;
        } else if (o1.getBalance() > o2.getBalance()) {
            return 1;
        } else {
            return 0;
        }
    }
}

class HolderNameComparator implements Comparator<Account> {
    public int compare(Account o1, Account o2) {
        return o1.getAccountHolderName().compareTo(o2.getAccountHolderName());
    }
}