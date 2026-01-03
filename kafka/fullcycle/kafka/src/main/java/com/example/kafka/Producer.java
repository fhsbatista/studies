package com.example.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final String TOPIC = "teste-groups";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String key, String msg) {
        final var completable = kafkaTemplate.send(TOPIC, key, msg);
        completable.whenComplete((result, ex) -> {
            final var payload = result.getProducerRecord().value();
            final var metadata = result.getRecordMetadata();
            final var partition = metadata.partition();

            System.out.println("Sent message: " + payload + ", on partition: " + partition);
        });
    }
}
