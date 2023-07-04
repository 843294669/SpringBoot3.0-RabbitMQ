package com.example.rabbit.workqueues;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WorkQueuesProducer {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.queue.name}")
    String queueName;

    public void produce(String message) {

        amqpTemplate.convertAndSend(queueName, message);

    }
}
