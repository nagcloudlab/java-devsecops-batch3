package com.npci.config;

import com.npci.repository.AccountRepository;
import com.npci.repository.JdbcAccountRepository;
import com.npci.service.TransferService;
import com.npci.service.UPITransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.npci")
public class TransferServiceConfiguration {

//    @Bean
//    public AccountRepository jdbcAccountRepository() {
//        return new JdbcAccountRepository();
//    }

//    @Bean
//    public TransferService upiTransferService(AccountRepository accountRepository) {
//        return new UPITransferService(accountRepository);
//    }

}
