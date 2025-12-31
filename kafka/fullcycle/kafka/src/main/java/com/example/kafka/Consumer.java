package com.example.kafka;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @PostConstruct
    public void init() {
        logger.info("Consumer inicializado e pronto para receber mensagens do tópico 'teste-groups'");
    }

    @KafkaListener(
        topics = "teste-groups"
    )
    public void consume(String payload) {
        logger.info("=== MENSAGEM RECEBIDA ===");
        logger.info("Payload: {}", payload);
    }
}
