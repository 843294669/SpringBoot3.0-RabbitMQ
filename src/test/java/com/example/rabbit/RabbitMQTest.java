package com.example.rabbit;

import com.example.rabbit.fanout.FanOutProducer;
import com.example.rabbit.simple.Producer;
import com.example.rabbit.topic.TopicProducer;
import com.example.rabbit.workqueues.WorkQueuesProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQTest {

    @Value("${spring.rabbitmq.queue.name}")
    String queueName;
    @Autowired
    Producer producer;
    @Autowired
    WorkQueuesProducer workQueuesProducer;
    @Autowired
    FanOutProducer fanOutProducer;
    @Autowired
    TopicProducer topicProducer;
    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void testSimple() {
        producer.produce("Hello World");
    }

    @Test
    public void testWorkQueues() throws InterruptedException {
        for (int i = 1; i < 100; i++) {
            workQueuesProducer.produce("Hello World " + i);
        }

    }

    @Test
    public void testFanOut() {
        fanOutProducer.produce("Hello World!");
    }

    @Test
    public void testTopic() {
        topicProducer.produce("topic2.*", "talk");
    }


}
