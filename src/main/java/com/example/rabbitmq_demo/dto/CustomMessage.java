package com.example.rabbitmq_demo.dto;


import java.io.Serializable;
import java.time.LocalDateTime;

// implements Serializable is important for the object to be transformed into a stream of bytes
public class CustomMessage implements Serializable {

    private String message;
    private LocalDateTime timestamp;

    // Default constructor is needed for deserialization
    public CustomMessage() {
    }

    public CustomMessage(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}