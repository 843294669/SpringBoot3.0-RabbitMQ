package com.example.rabbit.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.exchange.name}")
    String exchangeName;

    public void produce(String routeKey, String message) {

        amqpTemplate.convertAndSend(exchangeName, routeKey, message);

    }
}
