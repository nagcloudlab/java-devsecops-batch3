package com.npci.repository;

import com.npci.model.Account;

import java.util.Optional;

public interface AccountRepository {
    // Define methods for account operations, e.g., findById, save, delete, etc.
    // Example:
    Optional<Account> findByAccountNumber(String accountNumber);

    Account update(Account account);
}
