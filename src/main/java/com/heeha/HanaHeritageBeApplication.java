package com.heeha;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableBatchProcessing
public class HanaHeritageBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanaHeritageBeApplication.class, args);
    }

}
