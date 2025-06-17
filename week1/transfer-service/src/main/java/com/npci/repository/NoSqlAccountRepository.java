package com.npci.repository;

import com.npci.model.Account;

/**
 * team : red
 */

public class NoSqlAccountRepository implements AccountRepository {

    String url = System.getenv("MONGO_DB_URL");
    String user = System.getenv("MONGO_DB_USER");
    String password = System.getenv("MONGO_DB_PASSWORD");

    public NoSqlAccountRepository() {
        System.out.println("NoSqlAccountRepository component initialized.");
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
