package com.npci.config;

import com.npci.repository.AccountRepository;
import com.npci.repository.JdbcAccountRepository;
import com.npci.service.TransferService;
import com.npci.service.UpiTransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.npci")
public class TransferServiceConfiguration {

    // explain about your components here
//    @Bean
//    public AccountRepository jdbcAccountRepository() {
//        return new JdbcAccountRepository();
//    }

//    @Bean
//    public TransferService transferService(AccountRepository accountRepository) {
//        return new UpiTransferService(accountRepository);
//    }


    @Bean
    public DataSource dataSource() {
        // datasource libraries
        // HikariCP, Apache DBCP, Tomcat JDBC....
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5433/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

}