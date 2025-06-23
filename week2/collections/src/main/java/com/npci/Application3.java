package com.npci;

import com.npci.model.Account;

import java.util.*;

public class Application3 {
    public static void main(String[] args) {


        //compareListCollection(new ArrayList<>(1000000));
        //compareListCollection(new LinkedList<>());

        // List<Account> accounts = new Vector<>();
        List<Account> accounts = new ArrayList<>();
        // List<Account> accounts = new LinkedList<>();

        accounts.add(new Account("A2", "Jane", 2000.00));
        accounts.add(new Account("A1", "John", 1000.00));
        accounts.add(new Account("A3", "Doe", 3000.00));
        accounts.add(new Account("A4", "Alice", 4000.00));
        accounts.add(new Account("A5", "Bob", 5000.00));
        accounts.add(new Account("A5", "Bob", 5000.00));

        //Collections.sort(accounts); // select * from accounts order by accountId asc

        //BalanceComparatorDesc balanceComparatorDesc = new BalanceComparatorDesc();
        //Collections.sort(accounts, balanceComparatorDesc); // select * from accounts order by balance desc

//        AccountHolderNameComparator accountHolderNameComparator = new AccountHolderNameComparator();
//        Collections.sort(accounts,accountHolderNameComparator);


        displayAccounts(accounts);


    }


    private static void displayAccounts(List<Account> accounts) {
        System.out.println("Account List:");
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private static void compareListCollection(List<Account> list) {
        // add 1M accounts into list
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(new Account("A" + i, "User" + i, Math.random() * 10000));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to add 1M accounts: " + (endTime - startTime) + " ms");
    }

}


class BalanceComparatorDesc implements Comparator<Account> {
    public int compare(Account o1, Account o2) {
        if (o2.getBalance() < o1.getBalance()) {
            return -1;
        } else if (o2.getBalance() > o1.getBalance()) {
            return 1;
        } else {
            return 0;
        }
    }
}

class AccountHolderNameComparator implements Comparator<Account> {
    public int compare(Account o1, Account o2) {
        return o1.getAccountHolderName().compareTo(o2.getAccountHolderName());
    }
}
