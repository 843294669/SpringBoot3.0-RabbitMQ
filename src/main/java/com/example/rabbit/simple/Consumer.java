package com.example.rabbit.simple;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
@RabbitListener(queuesToDeclare = @Queue("${spring.rabbitmq.queue.name}"))
public class Consumer {

    @RabbitHandler
    public void handleMessage(String message) {
        // 消息处理逻辑
        System.out.println("Received message: " + message);
    }
}