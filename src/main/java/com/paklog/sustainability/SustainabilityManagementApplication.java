package com.paklog.sustainability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories
@EnableKafka
@EnableScheduling
public class SustainabilityManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SustainabilityManagementApplication.class, args);
    }
}
