package com.npci;

import com.npci.repository.AccountRepository;
import com.npci.repository.JdbcAccountRepository;
import com.npci.service.TransferService;
import com.npci.service.UpiTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*

developer complexities
-------------------------

-> creating & wiring components

 */

public class Application {

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    public static void main(String[] args) {

        //------------------------------------------------------
        // init / boot phase
        //------------------------------------------------------
        logger.info("-".repeat(50));

        AccountRepository accountRepository = new JdbcAccountRepository();
        TransferService transferService = new UpiTransferService(accountRepository);

        logger.info("-".repeat(50));
        //-------------------------------------------------------
        // run phase
        //-------------------------------------------------------
        logger.info("-".repeat(50));
        try {
            transferService.initiateTransfer("1234567890", "0987654321", 1000);
            logger.info("-".repeat(25));
            transferService.initiateTransfer("1234567890", "0987654321", 1000);
        } catch (Exception e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }

        logger.info("-".repeat(50));
        //--------------------------------------------------------
        // shutdown phase
        //--------------------------------------------------------

    }

}