package com.npci.repository;

public class AccountRepositoryFactory {

    public static AccountRepository getAccountRepository(String dbType) {
        if (dbType.equalsIgnoreCase("sql")) {
            return new SqlAccountRepository();
        }
        if (dbType.equalsIgnoreCase("nosql")) {
            return new NoSqlAccountRepository();
        }
        throw new IllegalArgumentException("Unknown db type");
    }

}
