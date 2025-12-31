package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaApplication {

    private static final Logger logger = LoggerFactory.getLogger(KafkaApplication.class);

    public static void main(String[] args) {
        final var context = SpringApplication.run(KafkaApplication.class, args);
        logger.info("passei");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Encerrando aplicação...");
            context.close();
        }));
        
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
