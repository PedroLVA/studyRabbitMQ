package com.example.rabbitmq_demo.dto;

import java.time.LocalDateTime;

public class MessageRequest {
    private String msg;
    private LocalDateTime date;
    private String metadados;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
