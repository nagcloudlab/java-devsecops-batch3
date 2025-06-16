package com.npci.service;

import com.npci.model.Account;
import com.npci.repository.AccountRepository;

/**
 * 
 * design issues
 * ---------------
 * -> tight-coupling b/w b/w dependent & dependency
 * => cant extend with new features easily
 * -> unit testing is difficult
 * => dev / bug-fixing time increases
 * 
 * performance issues
 * -----------------
 * -> creating / destroying the dependency object every time
 * => resource use high, responsiveness low
 * 
 * --------------------------------------------------------
 * // why these issues?
 * --------------------------------------------------------
 * 
 * -> dependent itself is managing it's own dependency
 * 
 * Solution:
 * 
 * -> Don't create dependency in dependent's home ( class ) -> use Factory
 * 
 * is this fixed?
 * 
 * -> with pattern, we can fix only design issues, still we have 'performance'
 * issues
 * 
 * best solution:
 * 
 * -> Don't create , Don't get dependency from factory, 'inject' by
 * third-person.
 * ( principle : Dependency Inversion Principle )
 * 
 * 
 * 
 * 
 */

public class UPITransferService {

    private AccountRepository accountRepository;

    // constructor
    public UPITransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository; //
        System.out.println("UPITransferService initialized");
    }

    public void transfer(double amount, String fromAccountNumber, String toAccountNumber) {
        System.out.println("transfer initiated...");
        // SqlAccountRepository accountRepository = new SqlAccountRepository(); // Dont
        // create
        // AccountRepository accountRepository=
        // AccountRepositoryFactory.getAccountRepository("nosql"); // Dont lookup on
        // factory
        // load 'from' account details
        Account fromAccount = accountRepository.loadAccount(fromAccountNumber);
        // load 'to' account details
        Account toAccount = accountRepository.loadAccount(toAccountNumber);
        // validate the transfer amount
        if (fromAccount.balance < amount) {
            throw new IllegalArgumentException("Insufficient balance in the 'from' account");
        }
        // debit the amount from 'from' account
        fromAccount.balance -= amount;
        // credit the amount to 'to' account
        toAccount.balance += amount;
        // update 'from' account
        accountRepository.updateAccount(fromAccount);
        // update 'to' account
        accountRepository.updateAccount(toAccount);
        // return a confirmation message or transaction ID
        System.out.println("transfer completed successfully");
    }

}
