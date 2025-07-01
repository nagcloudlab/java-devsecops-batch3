package com.npci.service;

import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.model.Account;
import com.npci.model.TransferHistory;
import com.npci.repository.AccountRepository;
import com.npci.repository.TransferHistoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class UpiTransferService implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger("transfer-service");

    private final AccountRepository accountRepository;
    private final TransferHistoryRepository historyRepository;

    @Autowired
    public UpiTransferService(
            @Qualifier("jpaAccountRepository") AccountRepository accountRepository,
            TransferHistoryRepository historyRepository
    ) {
        this.accountRepository = accountRepository;
        this.historyRepository = historyRepository;
        logger.info("UPI Transfer Service initialized with AccountRepository: {} and Cassandra",
                accountRepository.getClass().getSimpleName());
    }

    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {AccountNotFoundException.class, AccountBalanceException.class}
    )
    @Override
    public String initiateTransfer(String fromAccountNumber, String toAccountNumber, double amount) {
        logger.info("Initiating transfer of {} from {} to {}", amount, fromAccountNumber, toAccountNumber);

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("From account not found - " + fromAccountNumber));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("To account not found - " + toAccountNumber));

        if (fromAccount.getBalance() < amount) {
            logger.error("Insufficient balance in from account: {}. Balance: {}, Transfer: {}",
                    fromAccountNumber, fromAccount.getBalance(), amount);
            throw new AccountBalanceException("Insufficient balance in from account - " + fromAccountNumber);
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);

        logger.info("Transfer successful. New balances — From: {}, To: {}",
                fromAccount.getBalance(), toAccount.getBalance());

        try {
            // ✅ Save to Cassandra
            TransferHistory history = new TransferHistory(fromAccountNumber, toAccountNumber, BigDecimal.valueOf(amount));
            historyRepository.save(history);
            logger.info("Transfer history saved to Cassandra with ID: {}", history.getId());
        }catch (Exception e){
            logger.error("Failed to save transfer history to Cassandra: {}", e.getMessage());
            // Optionally, you can throw a custom exception or handle it as needed
        }

        return "Transfer of " + amount + " from " + fromAccountNumber + " to " + toAccountNumber + " was successful.";
    }
}
