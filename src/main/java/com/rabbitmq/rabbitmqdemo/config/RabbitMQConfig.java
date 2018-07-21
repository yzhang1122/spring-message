package com.rabbitmq.rabbitmqdemo.config;

import static com.rabbitmq.rabbitmqdemo.constants.Constants.DIRECT_EXCHANGE;
import static com.rabbitmq.rabbitmqdemo.constants.Constants.FANOUT_EXCHANGE;
import static com.rabbitmq.rabbitmqdemo.constants.Constants.HEADERS_EXCHANGE;
import static com.rabbitmq.rabbitmqdemo.constants.Constants.MY_QUEUE;
import static com.rabbitmq.rabbitmqdemo.constants.Constants.TOPIC_EXCHANGE;

import com.rabbitmq.rabbitmqdemo.listener.RabbitMQMessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  Queue myQueue() {
    return QueueBuilder.durable(MY_QUEUE)
        .autoDelete()
        .exclusive()
        .build();
  }

  @Bean
  Exchange directExchange() {
    return ExchangeBuilder.directExchange(DIRECT_EXCHANGE)
        .autoDelete()
        .build();
  }

  @Bean
  Exchange topicExchange() {
    return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE)
        .autoDelete()
        .durable(true)
        .build();
  }

  @Bean
  Exchange fanoutExchange() {
    return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE)
        .autoDelete()
        .durable(true)
        .build();
  }

  @Bean
  Exchange headersExchange() {
    return ExchangeBuilder.headersExchange(HEADERS_EXCHANGE)
        .durable(true)
        .ignoreDeclarationExceptions()
        .build();
  }

  @Bean
  Binding binding() {
    return BindingBuilder
        .bind(myQueue())
        .to(topicExchange())
        .with("topic")
        .noargs();
  }

  @Bean
  ConnectionFactory connectionFactory() {
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
    cachingConnectionFactory.setUsername("guest");
    cachingConnectionFactory.setPassword("guest");
    return cachingConnectionFactory;
  }

  @Bean
  MessageListenerContainer messageListenerContainer() {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
    simpleMessageListenerContainer.setQueues(myQueue());
    simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
    return simpleMessageListenerContainer;
  }
}
