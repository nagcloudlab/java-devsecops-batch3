package org.npci;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GreetController.class);

    @Timed(value = "greet.timer", description = "Time taken to process /greet endpoint", histogram = true)
    @GetMapping("/greet")
    public String greet() {
        logger.info("Starting /greet handler...");
        try {
            Thread.sleep(200); // Simulate processing time
        } catch (InterruptedException ignored) {
        }
        logger.info("Finished /greet handler.");
        return "Hello from DevSecOps!";
    }
}
