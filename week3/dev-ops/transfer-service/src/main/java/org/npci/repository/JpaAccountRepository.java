package org.npci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.npci.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        Account account = em.find(Account.class, accountNumber);
        if (account != null) {
            return Optional.of(account);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void update(Account account) {
        if (account != null) {
            em.merge(account);
        } else {
            throw new IllegalArgumentException("Account cannot be null");
        }
    }

    @Override
    public void save(Account account) {
        if (account != null) {
            em.persist(account);
        } else {
            throw new IllegalArgumentException("Account cannot be null");
        }
    }

    @Override
    public void deleteAll() {
        em.createQuery("DELETE FROM Account").executeUpdate();
    }

}
