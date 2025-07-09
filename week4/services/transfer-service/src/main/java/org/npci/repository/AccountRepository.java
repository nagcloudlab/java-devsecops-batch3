package org.npci.repository;

import org.npci.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByAccountNumber(String accountNumber);
    void update(Account account);
    void save(Account account);
    void deleteAll();
}
