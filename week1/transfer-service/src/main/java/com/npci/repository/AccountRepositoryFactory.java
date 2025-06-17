package com.npci.repository;

// Factory class to create instances of AccountRepository based on the database type
public class AccountRepositoryFactory {
    // Factory method to get an instance of AccountRepository
    public static AccountRepository getAccountRepository(String dbType) {
        if (dbType.equalsIgnoreCase("nosql")) {
            return new NoSqlAccountRepository();
        } else if (dbType.equalsIgnoreCase("sql")) {
            return new SqlAccountRepository();
        } else {
            throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
    }
}
