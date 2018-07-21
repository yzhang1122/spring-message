package com.rabbitmq.rabbitmqdemo.controller;

import static com.rabbitmq.rabbitmqdemo.constants.Constants.TOPIC_EXCHANGE;

import com.rabbitmq.rabbitmqdemo.model.SimpleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WebController {

  @Autowired
  RabbitTemplate rabbitTemplate;

  @RequestMapping("/send")
  public String sendMessage() {

    SimpleMessage simpleMessage = new SimpleMessage();
    simpleMessage.setName("FirstMessage");
    simpleMessage.setDescription("simpleDescription");

    rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, "topic", simpleMessage);
    log.info(">>> sending message");
    return "Send message to RabbitMq";
  }

  @RequestMapping("/read")
  public String receiveMessage() {

    return "read message from RabbitMq";
  }
}
