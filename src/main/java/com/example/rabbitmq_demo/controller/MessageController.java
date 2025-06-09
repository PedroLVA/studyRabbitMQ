package com.example.rabbitmq_demo.controller;

import com.example.rabbitmq_demo.dto.CustomMessage;
import com.example.rabbitmq_demo.service.MessageProducer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController()
@RequestMapping(value = "/message")
public class MessageController {

    private final MessageProducer messageProducer;


    public MessageController( MessageProducer messageProducer) {
        this.messageProducer = messageProducer;

    }

    @PostMapping
    public void sendMessage(@RequestBody CustomMessage message){
        message.setTimestamp(LocalDateTime.now());
        messageProducer.sendMessage(message);
    }
}
