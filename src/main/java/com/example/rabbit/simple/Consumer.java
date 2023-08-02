package com.example.rabbit.simple;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class Consumer {

    @RabbitListener(queuesToDeclare = @Queue("${spring.rabbitmq.queue.name}"))
    public void handleMessage(Channel channel, @Payload Message message) throws UnsupportedEncodingException {
        // 消息处理逻辑
        String msg = new String(message.getBody(), "UTF-8");
        System.out.println("Received message: " + msg);
        // 配置文件手动确认消息 acknowledge-mode: manual
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            // 消息重试
            try {
                // multiple = false 只拒绝当前消息，requeue = false 放回队列，防止死信
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ex) {
                // 消息丢弃
                try {
                    channel.basicReject(deliveryTag, false);
                } catch (IOException exc) {
                    // 异常处理和日志记录
                    ex.printStackTrace();
                }
            }
        }
    }

    @RabbitListener(queuesToDeclare = @Queue("${spring.rabbitmq.queue.name}"))
    @RabbitHandler
    public void handleMessage(Message message) throws UnsupportedEncodingException {
        // 自动确认消息
        String msg = new String(message.getBody(), "UTF-8");
        System.out.println("Received message: " + msg);
    }
}