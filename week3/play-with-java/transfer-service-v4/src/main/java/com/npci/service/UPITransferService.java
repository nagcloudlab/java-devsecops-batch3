package com.npci.service;

import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.model.Account;
import com.npci.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UPITransferService implements TransferService {
    private static Logger logger = LoggerFactory.getLogger("transfer-service");
    private AccountRepository accountRepository;

    @Autowired
    public UPITransferService(@Qualifier("jpaAccountRepository") AccountRepository accountRepository) {
        logger.info("UPITransferService initialized with AccountRepository: {}", accountRepository.getClass());
        this.accountRepository = accountRepository;
    }

    // ACID
    @Transactional(
            transactionManager = "transactionManager",
            //rollbackFor = {AccountNotFoundException.class, AccountBalanceException.class}
            noRollbackFor = {AccountNotFoundException.class, AccountBalanceException.class},
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public String initiateTransfer(String fromAccountNumber, String toAccountNumber, double amount) {
        logger.info("Initiating transfer of {} from {} to {}", amount, fromAccountNumber, toAccountNumber);

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountNumber));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException(toAccountNumber));

        if (fromAccount.getBalance() < amount) {
            logger.error("Insufficient balance in the source account: {}", fromAccountNumber);
            throw new AccountBalanceException("Insufficient balance in the source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.update(fromAccount);
        //..
        accountRepository.update(toAccount);

        logger.info("Transfer of {} from {} to {} completed successfully", amount, fromAccountNumber, toAccountNumber);
        return "Transfer of " + amount + " from " + fromAccountNumber + " to " + toAccountNumber + " initiated successfully.";

    }

}
