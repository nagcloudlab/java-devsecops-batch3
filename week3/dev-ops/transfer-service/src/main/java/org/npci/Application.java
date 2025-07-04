package org.npci;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("org.npci")
@EntityScan("org.npci.model")
public class Application {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(Application.class, args);
    }

}
