package com.example.kafka.controller;

import com.example.kafka.service.ProducerService;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Random;

@RestController
@RequestMapping("/price")
public class ProducerController {

    final ProducerService kafkaService;

    public ProducerController(ProducerService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/update")
    void updatePrice(@RequestParam(name = "message") String message) {
        this.kafkaService.updatePrice(message);
    }

    @PostMapping("/auto-update")
    void autoUpdatePrice() {
        double value = 1000.00;
        while (true) {
            try {
                value = getUpdatedValue(value);
                Thread.sleep(1000);
                this.kafkaService.updatePrice(String.valueOf(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    double getUpdatedValue(double value) {
        Random random = new Random();
        int randomInt = random.nextInt(1001) - 500;
        double randomDouble = randomInt / 100.0;
        value = value + randomDouble;
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(value));
    }
}
