package com.npci.repository;

import com.npci.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JdbcAccountRepository implements AccountRepository {

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    public  JdbcAccountRepository() {
        logger.info("JdbcAccountRepository initialized");
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        logger.info("Finding account by account number: {}", accountNumber);
        // ... Implement JDBC logic to find account by account number
        return Optional.of(new Account(accountNumber, "John Doe", 1000.0));
    }

    @Override
    public void update(Account account) {
        logger.info("Updating account details for account number: {}", account.getAccountNumber());
        // ... Implement JDBC logic to update account details
    }
}
