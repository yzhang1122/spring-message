package com.rabbitmq.rabbitmqdemo.controller;

import com.rabbitmq.rabbitmqdemo.model.SimpleMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

  @Autowired
  RabbitTemplate rabbitTemplate;

  @RequestMapping("/send")
  public String sendMessage() {

    SimpleMessage simpleMessage = new SimpleMessage();
    simpleMessage.setName("FirstMessage");
    simpleMessage.setDescription("simpleDescription");

    rabbitTemplate.convertAndSend("TestExchage", "testRouting", simpleMessage);
    System.out.println(">>> sending message");
    return "Send message to RabbitMq";
  }

  @RequestMapping("/read")
  public String receiveMessage() {



    return "read message from RabbitMq";
  }
}
