package com.example.kafka.service;

import com.example.kafka.config.AppConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class ProducerService {

    final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void updatePrice(String price) {
        this.kafkaTemplate.send(AppConstants.TOPIC_NAME, price);
    }
}
