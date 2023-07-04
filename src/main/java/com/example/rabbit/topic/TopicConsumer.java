package com.example.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitHandler
    @RabbitListener(bindings  = @QueueBinding(value = @Queue("${spring.rabbitmq.queue.name1}"),
            exchange = @Exchange("${spring.rabbitmq.exchange.name}"), key = "topic1.*"))
    public void handleMessage1(String message) {
        // 消息处理逻辑
        System.out.println("Received message 1: " + message);
    }

    @RabbitHandler
    @RabbitListener(bindings  = @QueueBinding(value = @Queue("${spring.rabbitmq.queue.name2}"),
            exchange = @Exchange("${spring.rabbitmq.exchange.name}"), key = "topic2.*"))
    public void handleMessage2(String message) {
        // 消息处理逻辑
        System.out.println("Received message 2: " + message);
    }

    @RabbitHandler
    @RabbitListener(bindings  = @QueueBinding(value = @Queue("${spring.rabbitmq.queue.name3}"),
            exchange = @Exchange("${spring.rabbitmq.exchange.name}"), key = "topic3.*"))
    public void handleMessage3(String message) {
        // 消息处理逻辑
        System.out.println("Received message 3: " + message);
    }
}