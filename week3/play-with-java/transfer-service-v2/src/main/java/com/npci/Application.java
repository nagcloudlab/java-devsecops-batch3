package com.npci;

import com.npci.config.TransferServiceConfiguration;
import com.npci.repository.AccountRepository;
import com.npci.repository.JdbcAccountRepository;
import com.npci.service.TransferService;
import com.npci.service.UpiTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        ConfigurableApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TransferServiceConfiguration.class);

        logger.info("-".repeat(50));
        //-------------------------------------------------------
        // run phase
        //-------------------------------------------------------
        logger.info("-".repeat(50));
        try {
            TransferService transferService = applicationContext.getBean(TransferService.class);
            transferService.initiateTransfer("123456789013", "123456789012", 1000);
            logger.info("-".repeat(25));
            transferService.initiateTransfer("123456789013", "123456789012", 1000);
        } catch (Exception e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }

        logger.info("-".repeat(50));
        //--------------------------------------------------------
        // shutdown phase
        //--------------------------------------------------------

    }

}