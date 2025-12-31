package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.UUID;

@SpringBootApplication
@EnableKafka
public class KafkaApplication {

    private static final Logger logger = LoggerFactory.getLogger(KafkaApplication.class);

    public static void main(String[] args) throws InterruptedException {
        final var context = SpringApplication.run(KafkaApplication.class, args);
        logger.info("passei");

        final var producer = context.getBean(Producer.class);

        for (int i = 0; i<200; i++) {
            producer.send("", UUID.randomUUID().toString());
            Thread.sleep(200);
        }

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
