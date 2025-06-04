package com.example.rabbitmq_demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    // @Value("${app.rabbitmq.queue}") // Not strictly needed here if @RabbitListener refers to the property
    // private String queueName;

    // In MessageConsumer.java
    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(String message) {
        LOGGER.info(String.format("Received message -> %s", message));
        try {
            Thread.sleep(5000); // Sleep for 5 seconds
            LOGGER.info("Finished processing message -> " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Processing interrupted", e);
        }
    }
}