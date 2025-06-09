package com.example.rabbitmq_demo.service;

import com.example.rabbitmq_demo.dto.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    // Constructor injection
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Update the method to accept the CustomMessage object
    public void sendMessage(CustomMessage message) {
        LOGGER.info(String.format("Sending JSON message -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
