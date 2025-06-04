package com.example.rabbitmq_demo;

import com.example.rabbitmq_demo.service.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqDemoApplication.class, args);
	}

	// This bean will run once the application context is loaded
	@Bean
	public CommandLineRunner demo(MessageProducer producer) {
		return args -> {
			producer.sendMessage("Hello from Spring Boot and RabbitMQ!");
			producer.sendMessage("This is a test message.");
			// Send a few more to see them processed
			for (int i = 0; i < 5; i++) {
				producer.sendMessage("Test message number " + (i + 1));
				Thread.sleep(1000); // Optional: to see them appear one by one
			}
		};
	}
}