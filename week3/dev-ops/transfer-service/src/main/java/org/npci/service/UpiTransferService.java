package org.npci.service;

import org.npci.exception.AccountBalanceException;
import org.npci.exception.AccountNotFoundException;
import org.npci.model.Account;
import org.npci.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpiTransferService implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(UpiTransferService.class);
    private final AccountRepository accountRepository;

    @Autowired
    public UpiTransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void initiateTransfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new AccountBalanceException("Insufficient balance in sender account");
        }

        // Business logic
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);

        // Logging (avoid sensitive details)
        logger.info("Transferred {} from {} to {}", amount, mask(fromAccountNumber), mask(toAccountNumber));
    }

    private String mask(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) return "****";
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
}
