package org.npci.service;

import org.npci.model.Account;
import org.npci.repository.AccountRepository;

public class UpiTransferService implements TransferService {

    private AccountRepository accountRepository;

    public UpiTransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void initiateTransfer(String fromAccountNumber, String toAccountNumber, double amount) {

        Account fromAccount = accountRepository.findById(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("From account not found - " + fromAccountNumber));

        Account toAccount = accountRepository.findById(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("To account not found - " + toAccountNumber));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in from account - " + fromAccountNumber);
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() - amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);


    }
}
