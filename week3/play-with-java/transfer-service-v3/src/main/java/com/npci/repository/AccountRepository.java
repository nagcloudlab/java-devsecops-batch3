package com.npci.repository;

import com.npci.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByAccountNumber(String accountNumber);
    void update(Account account);
}
