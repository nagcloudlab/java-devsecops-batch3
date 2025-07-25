package org.npci;

import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    /*
    greet_timer_count
    greet_timer_sum
    greet_timer_max
     */
    @Timed(value = "greet.timer", description = "Time taken to process /greet endpoint", histogram = true)
    @GetMapping("/greet")
    public String greet() {
        logger.info("Starting /greet handler...");
        try {
            Thread.sleep(200); // Simulate processing time
        } catch (InterruptedException ignored) {}
        logger.info("Finished /greet handler.");
        return "Hello from DevSecOps!";
    }

    @GetMapping("/fail")
    public String fail() {
        throw new RuntimeException("Simulated error");
    }




}
