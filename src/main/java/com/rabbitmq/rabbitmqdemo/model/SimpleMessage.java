package com.rabbitmq.rabbitmqdemo.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleMessage implements Serializable {

  private static final long serialVersionUID = 782032525432868166L;
  private String name;
  private String description;
}
