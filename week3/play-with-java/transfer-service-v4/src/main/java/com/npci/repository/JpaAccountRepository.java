package com.npci.repository;

import com.npci.model.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAccountRepository implements AccountRepository{

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    @PersistenceContext
    private EntityManager entityManager;

    public JpaAccountRepository() {
        logger.info("JpaAccountRepository initialized");
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        logger.info("Finding account by account number: {}", accountNumber);
        Account account = entityManager.find(Account.class, accountNumber);
        return Optional.ofNullable(account);
    }

    @Override
    public Account update(Account account) {
        logger.info("Updating account: {}", account.getAccountNumber());
        return entityManager.merge(account);
    }
}
