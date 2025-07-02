package com.npci.repository;

import com.npci.model.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        Account account = entityManager.find(Account.class, accountNumber);
        return Optional.ofNullable(account);
    }

    @Override
    public void update(Account account) {
        entityManager.merge(account);
    }
}
