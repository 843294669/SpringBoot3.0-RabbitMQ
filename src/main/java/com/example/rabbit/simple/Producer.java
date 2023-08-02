package com.example.rabbit.simple;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue.name}")
    String queueName;

    public void produce(Message message) {

        rabbitTemplate.convertAndSend(queueName, message);

    }


}
