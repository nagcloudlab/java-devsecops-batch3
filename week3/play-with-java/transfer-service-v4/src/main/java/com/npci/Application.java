package com.npci;

import com.npci.exception.AccountBalanceException;
import com.npci.exception.AccountNotFoundException;
import com.npci.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/*

developer complexities

    -> creating & wiring components based on dependency inversion principle  -> spring F.W

 */


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.npci")
@EntityScan(basePackages = "com.npci.model")
public class Application {

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    public static void main(String[] args) {

        //----------------------------------------------------------
        // Init / boot
        //----------------------------------------------------------
        logger.info("-".repeat(50));

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Application.class, args);

        logger.info("-".repeat(50));
        //----------------------------------------------------------
        // Run
        //----------------------------------------------------------
        logger.info("-".repeat(50));
//
//        try {
//            TransferService transferService = applicationContext.getBean(TransferService.class);
//            transferService
//                    .initiateTransfer("123456789012", "123456789013", 100);
//            logger.info("-".repeat(25));
//            transferService
//                    .initiateTransfer("123456789012", "123456789013", 100);
//
//        } catch (AccountNotFoundException | AccountBalanceException e) {
//            logger.error("Error occurred while processing transfer: {}", e.getMessage());
//        } catch (Exception e) {
//            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
//        } finally {
//            logger.info("Transfer service operations completed.");
//        }

        logger.info("-".repeat(50));
        //----------------------------------------------------------
        // Shutdown
        //----------------------------------------------------------
        // applicationContext.close();

    }
}
