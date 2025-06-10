package com.example.rabbitmq_demo.service;

import com.example.rabbitmq_demo.dto.CustomMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private MessageProducer messageProducer;

    @BeforeEach
    void setUp(){
        ReflectionTestUtils.setField(messageProducer, "exchangeName", "test-exchange");
        ReflectionTestUtils.setField(messageProducer, "routingKey", "test-routing-key");
    }

    @Test
    void sendMessage_shouldCallConvertAndSend_withCorrectParameters() {

        String expectedExchange = "test-exchange";
        String expectedRoutingKey = "test-routing-key";
        CustomMessage message = new CustomMessage("Hello World!", LocalDateTime.now());

        messageProducer.sendMessage(message);

        verify(rabbitTemplate, times(1))
                .convertAndSend(expectedExchange, expectedRoutingKey, message);
    }

    @Test
    void sendMessage_shouldFAIL_whenExpectingWrongRoutingKey() {

        String expectedExchange = "test-exchange";

        String wrongRoutingKey = "this-is-not-the-correct-routing-key";
        CustomMessage message = new CustomMessage("Hello World!", LocalDateTime.now());


        messageProducer.sendMessage(message);


        verify(rabbitTemplate, times(1))
                .convertAndSend(expectedExchange, wrongRoutingKey, message);
    }

    @Test
    void sendMessage_shouldFAIL_whenExpectingTooManyInvocations() {

        String expectedExchange = "test-exchange";
        String expectedRoutingKey = "test-routing-key";
        CustomMessage message = new CustomMessage("Hello World!", LocalDateTime.now());

        messageProducer.sendMessage(message);


        verify(rabbitTemplate, times(2)) // <-- WRONG VERSION: We expect 2 calls
                .convertAndSend(expectedExchange, expectedRoutingKey, message);
    }

}