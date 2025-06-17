package com.npci.service;

import com.npci.model.Account;
import com.npci.repository.AccountRepository;

/**
 * author: blue-team
 */


/*
    design issues
    -------------
    -> dependent & dependent are tightly coupled
        => can't extend with new features easily
    -> unit testing is difficult
        => dev & big-fix slow down
    performance issues
    -------------------
    -> every time we transfer money, create a new instance of SqlAccountRepository
        => resource use high , performance low
    ----------------------------------------------------
 */

/**
 *
 *  why those issues
 *  -----------------
 *
 *      => dependent itself manages its own dependencies
 *
 *   Solution to design issues  ( Design Pattern )
 *   --------------------------
 *
 *      => Don't create dependencies inside dependent's home ( class ), use factory  pattern
 *
 *
 *   Solution to performance issues
 *   -----------------------------
 *
 *      => Don't lookup dependencies inside dependent's home ( class ),
 *         "inject" by third party
 *         principal => dependency inversion
 *
  */


public class UPITransferService implements TransferService{

    // HAS-A relationship  i.e composition
     private AccountRepository accountRepository; // Don't create

    // dependency injection
    public UPITransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        System.out.println("UPITransferService component initialized.");
    }

    public void transfer(double amount,String source,String target){
        System.out.println("Transfer Initiated");
        // SqlAccountRepository accountRepository=new SqlAccountRepository(); // Dont create
        // AccountRepository accountRepository= AccountRepositoryFactory.getAccountRepository("sql"); // Dont' lookup
        // load 'source' account d details
        Account sourceAccount=accountRepository.loadAccount(source);
        // load 'taget' account details
        Account targetAccount=accountRepository.loadAccount(target);
        // check if source account has sufficient balance
        if(sourceAccount.balance < amount){
            throw new RuntimeException("Insufficient balance in source account");
        }
        // deduct amount from source account
        System.out.println("Debiting " + amount + " from source account: " + source);
        sourceAccount.balance -= amount;
        // add amount to target account
        System.out.println("Crediting " + amount + " to target account: " + target);
        targetAccount.balance += amount;
        // update source account in repository
        accountRepository.updateAccount(sourceAccount);
        // update target account in repository
        accountRepository.updateAccount(targetAccount);
        System.out.println("Transfer Successful");
    }

}
