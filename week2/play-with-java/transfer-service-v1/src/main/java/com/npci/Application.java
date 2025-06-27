package com.npci;

import com.npci.repository.AccountRepository;
import com.npci.repository.JdbcAccountRepository;
import com.npci.service.TransferService;
import com.npci.service.UPITransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*

developer complexities

    -> creating & wiring components based on dependency inversion principle

 */

public class Application {

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    public static void main(String[] args) {

        //----------------------------------------------------------
        // Init / boot
        //----------------------------------------------------------
        logger.info("-".repeat(50));

        AccountRepository jdbcAccountRepository = new JdbcAccountRepository();
        TransferService transferService = new UPITransferService(jdbcAccountRepository);

        logger.info("-".repeat(50));
        //----------------------------------------------------------
        // Run
        //----------------------------------------------------------
        logger.info("-".repeat(50));
        transferService
                .initiateTransfer("1234567890", "0987654321", 1000);
        logger.info("-".repeat(25));
        transferService
                .initiateTransfer("1234567890", "0987654321", 500);

        logger.info("-".repeat(50));
        //----------------------------------------------------------
        // Shutdown
        //----------------------------------------------------------

    }
}
