package com.npci.repository;

import com.npci.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

@Component
public class JdbcAccountRepository implements AccountRepository {

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        logger.info("JdbcAccountRepository initialized");
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        logger.info("Finding account by account number: {}", accountNumber);
        String query = "select * from accounts where account_number = ?";
        Account account = jdbcTemplate.queryForObject(query, (rs, rowIndex) -> {
            String accNumber = rs.getString("account_number");
            String accHolderName = rs.getString("account_holder_name");
            double balance = rs.getDouble("balance");
            return new Account(accNumber, accHolderName, balance);
        }, accountNumber);
        return Optional.of(account);
    }

    @Override
    public Account update(Account account) {
        logger.info("Updating account: {}", account.getAccountNumber());
        String updateQuery = "update accounts set balance = ? where account_number = ?";
        jdbcTemplate.update(updateQuery, account.getBalance(), account.getAccountNumber());
        return account;
    }
}
