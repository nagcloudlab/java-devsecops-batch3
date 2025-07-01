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
        String sql = "select * from accounts where account_number = ?";
        Account account = jdbcTemplate.queryForObject(sql, (rs, rowIndex) -> {
            String accountHolderName = rs.getString("account_holder_name");
            double balance = rs.getDouble("balance");
            return new Account(accountNumber, accountHolderName, balance);
        }, accountNumber);
        return Optional.ofNullable(account);
    }

    @Override
    public void update(Account account) {
        logger.info("Updating account details for account number: {}", account.getAccountNumber());
        // ... Implement JDBC logic to update account details
        String sql = "update accounts set balance = ? where account_number = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getAccountNumber());
    }
}
