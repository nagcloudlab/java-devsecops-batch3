package com.npci.repository;

import com.npci.model.Account;

public interface AccountRepository {
    Account loadAccount(String number);
    void updateAccount(Account account);
}
