package com.example.rabbit.fanout;

import org.springframework.amqp.rabbit.annotation.*;

//@Component
public class FanOutConsumer {

    @RabbitHandler
    @RabbitListener(bindings  = @QueueBinding(value = @Queue("${spring.rabbitmq.queue.name1}"), exchange = @Exchange("${spring.rabbitmq.exchange.name}")))
    public void handleMessage1(String message) {
        // 消息处理逻辑
        System.out.println("Received message 1: " + message);
    }

    @RabbitHandler
    @RabbitListener(bindings  = @QueueBinding(value = @Queue("${spring.rabbitmq.queue.name2}"), exchange = @Exchange("${spring.rabbitmq.exchange.name}")))
    public void handleMessage2(String message) {
        // 消息处理逻辑
        System.out.println("Received message 2: " + message);
    }

    @RabbitHandler
    @RabbitListener(bindings  = @QueueBinding(value = @Queue("${spring.rabbitmq.queue.name3}"), exchange = @Exchange("${spring.rabbitmq.exchange.name}")))
    public void handleMessage3(String message) {
        // 消息处理逻辑
        System.out.println("Received message 3: " + message);
    }
}