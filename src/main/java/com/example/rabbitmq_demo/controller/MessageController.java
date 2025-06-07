package com.example.rabbitmq_demo.controller;

import com.example.rabbitmq_demo.service.MessageProducer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/message")
public class MessageController {

    private final MessageProducer messageProducer;


    public MessageController( MessageProducer messageProducer) {
        this.messageProducer = messageProducer;

    }

    @PostMapping
    public void sendMessage(@RequestBody String msg){
        messageProducer.sendMessage(msg);
    }
}
