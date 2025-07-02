package com.npci.service;

import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.model.Account;
import com.npci.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpiTransferService implements TransferService {

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    private AccountRepository accountRepository;

    @Autowired
    public UpiTransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        logger.info("UPI Transfer Service initialized with AccountRepository: {}", accountRepository.getClass().getSimpleName());
    }

    @Override
    public String initiateTransfer(String fromAccountNumber, String toAccountNumber, double amount) {
        logger.info("Initiating transfer of {} from {} to {}", amount, fromAccountNumber, toAccountNumber);

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("From account not found - " + fromAccountNumber));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("To account not found - " + toAccountNumber));

        if (fromAccount.getBalance() < amount) {
            logger.error("Insufficient balance in from account: {}. Current balance: {}, Transfer amount: {}",
                    fromAccountNumber, fromAccount.getBalance(), amount);
            throw new AccountBalanceException("Insufficient balance in from account - " + fromAccountNumber);
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);

        logger.info("Transfer of {} from {} to {} completed successfully. New balances - From: {}, To: {}",
                amount, fromAccountNumber, toAccountNumber, fromAccount.getBalance(), toAccount.getBalance());
        return "Transfer of " + amount + " from " + fromAccountNumber + " to " + toAccountNumber + " initiated successfully.";

    }
}
