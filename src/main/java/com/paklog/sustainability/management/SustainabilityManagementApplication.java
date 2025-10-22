package com.paklog.sustainability.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Sustainability Management
 *
 * Carbon tracking and green logistics optimization
 *
 * @author Paklog Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableKafka
@EnableMongoAuditing
public class SustainabilityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SustainabilityManagementApplication.class, args);
    }
}