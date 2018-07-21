package com.rabbitmq.rabbitmqdemo.listener;

import com.rabbitmq.rabbitmqdemo.model.SimpleMessage;
import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

@Slf4j
public class RabbitMQMessageListener implements MessageListener {

  @Override
  public void onMessage(Message message) {
    ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
    ObjectInput in;
    SimpleMessage simpleMessage = null;
    try {
      in = new ObjectInputStream(bis);
      simpleMessage = (SimpleMessage) in.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }

    log.info("<<< receiving message: {}", simpleMessage);
  }
}
