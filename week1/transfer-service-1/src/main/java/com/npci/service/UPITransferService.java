package com.npci.service;

import com.npci.model.Account;
import com.npci.repository.SqlAccountRepository;

public class UPITransferService {

    public UPITransferService(){
        System.out.println("UPITransferService initialized");
    }

    public void transfer(double amount, String fromAccountNumber, String toAccountNumber) {
        System.out.println("transfer initiated...");
        SqlAccountRepository accountRepository = new SqlAccountRepository();
        // load 'from' account details
        Account fromAccount = accountRepository.loadAccount(fromAccountNumber);
        // load 'to' account details
        Account toAccount = accountRepository.loadAccount(toAccountNumber);
        // validate the transfer amount
        if(fromAccount.balance< amount) {
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
