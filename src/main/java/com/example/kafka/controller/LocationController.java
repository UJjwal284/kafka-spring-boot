package com.example.kafka.controller;

import com.example.kafka.service.KafkaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {

    final KafkaService kafkaService;

    public LocationController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }


    @PostMapping("/update")
    ResponseEntity<?> updateLocation() {
        String location = String.valueOf((int) (Math.random() * 100) + 1);
        this.kafkaService.updateLocation(location);
        return new ResponseEntity<>(Map.of("message", "Location updated: " + location), HttpStatus.OK);
    }


}
