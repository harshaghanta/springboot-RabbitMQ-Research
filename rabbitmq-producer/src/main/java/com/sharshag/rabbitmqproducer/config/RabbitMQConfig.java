package com.sharshag.rabbitmqproducer.config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    
    
    /**
     *
     */
    private static final String ROUTING_STAR = "routing.*";
    public static final String ROUTING_B = "routing.B";    
    public static final String ROUTING_A = "routing.A";

    @Bean
    public Queue queueA() {
        return new Queue("queue.A", false);
    }

    @Bean
    public Queue queueB() {
        return new Queue("queue.B", false);
    }

    @Bean
    public Queue allQueue() {
        return new Queue("queue.All", false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    public FanoutExchange fanOutExchange() {
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("exchange.headers");
    }

    @Bean
    public Binding directBindingA(Queue queueA, DirectExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
    }

    @Bean
    public Binding directBindingB(Queue queueB, DirectExchange exchangeB) {
        return BindingBuilder.bind(queueB).to(exchangeB).with(ROUTING_B);
    }

    @Bean
    public Binding fanoutBindingA(Queue queueA, FanoutExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange);
    }

    @Bean
    public Binding fanoutBindingB(Queue queueB, FanoutExchange exchangeB) {
        return BindingBuilder.bind(queueB).to(exchangeB);
    }

    @Bean
    public Binding topicExchangeBindingA(Queue queueA, TopicExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
    }

    @Bean
    public Binding topicExchangeBindingB(Queue queueB, TopicExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_B);
    }

    @Bean
    public Binding topicExchangeBindingAll(Queue allQueue, TopicExchange exchange) {
        return BindingBuilder.bind(allQueue).to(exchange).with(ROUTING_STAR);
    }

    @Bean
    public Binding headersExchangeBindingA(Queue queueA, HeadersExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).where("header").matches("A");
    }

    @Bean
    public Binding headersExchangeBindingB(Queue queueB, HeadersExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).where("header").matches("B");
    }

    @Bean
    public MessageConverter  messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
         RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
         rabbitTemplate.setMessageConverter(messageConverter());
         return rabbitTemplate;
    }
    
}
