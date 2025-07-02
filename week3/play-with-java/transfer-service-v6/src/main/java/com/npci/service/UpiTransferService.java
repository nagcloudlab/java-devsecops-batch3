package com.npci.service;

import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.model.Account;
import com.npci.model.TransferHistory_By_Hour;
import com.npci.repository.AccountRepository;
import com.npci.repository.TransferHistoryByHourRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class UpiTransferService implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger("transfer-service");

    private final AccountRepository accountRepository;
    private final TransferHistoryByHourRepository historyByHourRepository;

    private static final DateTimeFormatter HOUR_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd-HH").withZone(ZoneId.of("Asia/Kolkata"));

    @Autowired
    public UpiTransferService(
            @Qualifier("jpaAccountRepository") AccountRepository accountRepository,
            TransferHistoryByHourRepository historyByHourRepository
    ) {
        this.accountRepository = accountRepository;
        this.historyByHourRepository = historyByHourRepository;
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
            // ✅ Save to new Cassandra table
            Instant now = Instant.now();
            String hourBucket = HOUR_FORMATTER.format(now);

            TransferHistory_By_Hour.TransferKey key = new TransferHistory_By_Hour.TransferKey();
            key.setHourBucket(hourBucket);
            key.setTimestamp(now);
            key.setTransactionId(UUID.randomUUID());

            TransferHistory_By_Hour record = new TransferHistory_By_Hour();
            record.setKey(key);
            record.setFromAccount(fromAccountNumber);
            record.setToAccount(toAccountNumber);
            record.setAmount(BigDecimal.valueOf(amount));

            historyByHourRepository.save(record);

            logger.info("Cassandra transfer history saved with key: {}", key.getTransactionId());

        } catch (Exception e) {
            logger.error("Failed to save Cassandra transfer history: {}", e.getMessage());
        }

        return "Transfer of " + amount + " from " + fromAccountNumber + " to " + toAccountNumber + " was successful.";
    }
}
