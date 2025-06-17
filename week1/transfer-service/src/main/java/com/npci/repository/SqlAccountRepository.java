package com.npci.repository;

import com.npci.model.Account;

/**
 * team : red
 */

public class SqlAccountRepository implements AccountRepository {

    String url = System.getenv("DB_URL");
    String user = System.getenv("DB_USER");
    String password = System.getenv("DB_PASSWORD");

    public SqlAccountRepository() {
        System.out.println("SqlAccountRepository component initialized.");
    }

    public Account loadAccount(String number) {
        System.out.println("Loading account details for: " + number);
        //......
        Account account = new Account();
        account.number = number;
        account.balance = 1000.00;
        return account;
    }

    public void updateAccount(Account account){
        System.out.println("Updating account details for: " + account.number);
        ///...
    }

}
