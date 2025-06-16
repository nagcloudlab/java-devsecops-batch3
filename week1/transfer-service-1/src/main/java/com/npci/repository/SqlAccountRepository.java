package com.npci.repository;

import com.npci.model.Account;

/**
 *
 * author : Foo team
 */

public class SqlAccountRepository implements AccountRepository {

    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String username = "postgres";
    String password = System.getenv("POSTGRESQL_PASSWORD");

    public SqlAccountRepository(){
        System.out.println("SqlAccountRepository initialized");
    }

    public Account loadAccount(String number) {
        System.out.println("Loading account: " + number);
        // logic to load account from SQL database
        Account account = new Account(); // object
        account.number = number;
        account.balance = 1000.00; // Example balance
        return account;
    }

    public void updateAccount(Account account) {
        // logic to update account in SQL database
        // For example, updating the balance
        System.out.println("Updating account "+ account.number );
    }


}
