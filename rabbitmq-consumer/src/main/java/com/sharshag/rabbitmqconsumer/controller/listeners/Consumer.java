package com.sharshag.rabbitmqconsumer.controller.listeners;

import com.sharshag.rabbitmqconsumer.model.Message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {
    
    @RabbitListener(queues = "queue.A")
    public void receiveMessageFromQueueA(Message message) {
        log.info("Message Received from QueueA: {}" , message);
    }

    @RabbitListener(queues = "queue.B")
    public void receiveMessageFromQueueB(Message message) {
        log.info("Message Received from QueueB: {}" , message);
    }

    @RabbitListener(queues = "queue.All")
    public void receiveMessageFromQueueAll(Message message) {
        log.info("Message Received from QueueAll: {}" , message);
    }
}
