package com.npci;

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
-------------------------

-> creating & wiring components

 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.npci")
@EntityScan(basePackages = "com.npci.model")
public class Application {

    private static Logger logger = LoggerFactory.getLogger("transfer-service");

    public static void main(String[] args) {

        //------------------------------------------------------
        // init / boot phase
        //------------------------------------------------------
        logger.info("-".repeat(50));

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Application.class, args);

        logger.info("-".repeat(50));
        //-------------------------------------------------------
        // run phase
        //-------------------------------------------------------
        logger.info("-".repeat(50));
        try {
            TransferService transferService = applicationContext.getBean(TransferService.class);
            transferService.initiateTransfer("123456789012", "123456789013", 100);
        } catch (Exception e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }

        logger.info("-".repeat(50));
        //--------------------------------------------------------
        // shutdown phase
        //--------------------------------------------------------

    }

}