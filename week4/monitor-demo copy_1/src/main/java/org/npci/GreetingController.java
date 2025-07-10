package org.npci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("/greet")
    public String greet() {
        logger.info("Starting /greet handler...");
        try {
            Thread.sleep(200); // Simulate processing time
        } catch (InterruptedException ignored) {}
        logger.info("Finished /greet handler.");
        return "Hello from DevSecOps!";
    }
}
