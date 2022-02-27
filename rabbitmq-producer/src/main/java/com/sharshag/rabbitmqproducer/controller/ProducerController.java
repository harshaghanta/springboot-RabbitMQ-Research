package com.sharshag.rabbitmqproducer.controller;


import com.sharshag.rabbitmqproducer.model.Message;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    private FanoutExchange fanoutExchange;

    @Autowired
    private TopicExchange topiExchange;

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private HeadersExchange headersExchange;
    @PostMapping("/post")
    public String sendMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message);
        return "Message sent successfully";
    }

    @PostMapping("/post-fanout")
    public String sendFanoutMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
        return "Message sent successfully";
    }

    @PostMapping("/post-topic")
    public String sendTopicMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend(topiExchange.getName(), "routing.A", message);
        return "Message sent successfully";
    }

    @PostMapping("/post-headers/{header}")
    public String sendHeadersMessage(@RequestBody Message message, @PathVariable(name = "header" ) String header) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("header", header);
        org.springframework.amqp.core.Message message2 = messageConverter.toMessage(message, messageProperties);
        rabbitTemplate.send(headersExchange.getName(), "", message2);

        return "Message sent successfully";
    }

    @PostMapping("/post-default")
    public String sendDefaultMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend("", "queue.A", message);
        return "Message sent successfully";
    }
    
}
