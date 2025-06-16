package com.npci;

import com.npci.repository.AccountRepository;
import com.npci.repository.AccountRepositoryFactory;
import com.npci.service.UPITransferService;

public class Application {

    public static void main(String[] args) {

        // Init/boot phase
        // based on configurations, initialize the application
        System.out.println("----------------------------------------");

        AccountRepository accountRepository = AccountRepositoryFactory.getAccountRepository("nosql");
        UPITransferService upiTransferService = new UPITransferService(accountRepository);

        System.out.println("----------------------------------------");

        // Run phase
        System.out.println("----------------------------------------");

        upiTransferService.transfer(100, "user1@upi", "user2@upi");
        System.out.println();
        upiTransferService.transfer(200, "user2@upi", "user1@upi");

        System.out.println("----------------------------------------");

        // Shutdown phase
        System.out.println("----------------------------------------");
        // Perform any cleanup or resource release if necessary
        System.out.println("----------------------------------------");

    }

}

