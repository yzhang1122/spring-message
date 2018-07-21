package com.rabbitmq.rabbitmqdemo.listener;

import com.rabbitmq.rabbitmqdemo.model.SimpleMessage;
import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQMessageListener implements MessageListener {

  @Override
  public void onMessage(Message message) {
    ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
    ObjectInput in = null;
    SimpleMessage simpleMessage = null;
    try {
      in = new ObjectInputStream(bis);
      simpleMessage = (SimpleMessage) in.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(">>> receiving message: " + simpleMessage);
  }
}
