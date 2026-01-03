package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kafka.entity.Message;
import com.example.kafka.repository.MessageRepository;

import jakarta.annotation.PostConstruct;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    
    private final MessageRepository messageRepository;

    public Consumer(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PostConstruct
    public void init() {
        logger.info("Consumer inicializado e pronto para receber mensagens do tópico 'teste-groups'");
    }

    @KafkaListener(
        topics = "teste-groups"
    )
    @Transactional
    public void consume(String payload) {
        logger.info("=== MENSAGEM RECEBIDA ===");
        logger.info("Payload: {}", payload);
        
        // Transformar a string recebida em uma Message e salvar no PostgreSQL
        Message message = new Message(payload);
        Message savedMessage = messageRepository.save(message);
        
        logger.info("Mensagem salva no PostgreSQL com ID: {}", savedMessage.getId());
    }
}
