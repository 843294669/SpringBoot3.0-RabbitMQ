package com.example.rabbit.workqueues;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
public class WorkQueuesConsumer {

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("${spring.rabbitmq.queue.name}"))
    public void handleMessage1(String message) {
        // 消息处理逻辑
        System.out.println("Received message 1: " + message);
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("${spring.rabbitmq.queue.name}"))
    public void handleMessage2(String message) {
        // 消息处理逻辑
        System.out.println("Received message 2: " + message);
    }
}