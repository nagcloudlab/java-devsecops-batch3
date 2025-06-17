package com.npci;

import com.npci.repository.AccountRepository;
import com.npci.repository.AccountRepositoryFactory;
import com.npci.service.UPITransferService;

public class Application {

    public static void main(String[] args) {

        // init / boot phase
        // based on configuration, create the necessary components & assemble them
        System.out.println("----------------------------------");
        AccountRepository accountRepository = AccountRepositoryFactory.getAccountRepository("nosql");
        UPITransferService upiTransferService = new UPITransferService(accountRepository);
        System.out.println("----------------------------------");

        // run phase
        System.out.println("----------------------------------");
        upiTransferService.transfer(100, "1234567890@upi", "9876543210@upi");
        System.out.println();
        upiTransferService.transfer(200, "1234567890@upi", "9876543210@upi");
        System.out.println("----------------------------------");

        // shutdown phase
        System.out.println("----------------------------------");
        System.out.println("Shutting down the application...");
        System.out.println("----------------------------------");

    }

}
