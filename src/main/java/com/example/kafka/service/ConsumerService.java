package com.example.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    final SimpMessagingTemplate template;

    public ConsumerService(SimpMessagingTemplate template) {
        this.template = template;
    }

    @KafkaListener(topics = "price-update", groupId = "group-1")
    void updatedPrice(String value) {
        template.convertAndSend("/topic/price", value);
    }
}
