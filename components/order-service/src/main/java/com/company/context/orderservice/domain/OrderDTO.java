package com.company.context.orderservice.domain;

import com.company.context.orderservice.data.OrderStatus;
import com.company.context.orderservice.data.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("order")
public class OrderDTO {

  @JsonProperty("uuid")
  private String uuid;

  @JsonProperty("status")
  private OrderStatus status;

  public static OrderDTO of(Order order){
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setUuid(order.getUuid());
    orderDTO.setStatus(order.getStatus());
    return orderDTO;
  }
}
