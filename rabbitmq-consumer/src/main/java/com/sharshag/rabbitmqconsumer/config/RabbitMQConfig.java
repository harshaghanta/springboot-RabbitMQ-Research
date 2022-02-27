package com.sharshag.rabbitmqconsumer.config;

// import org.springframework.amqp.core.DirectExchange;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    
    public static final String EXCHANGE_DIRECT = "exchange.direct";

    // @Bean
    // public DirectExchange directExchange() {
    //      DirectExchange directExchange = new DirectExchange(EXCHANGE_DIRECT);
    //      return directExchange;
    // } 

    // @Bean
    // public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    //      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //      rabbitTemplate.setMessageConverter(messageConverter());
    //      return rabbitTemplate;
    // }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    
}
