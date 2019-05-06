package com.company.context.orderservice;

import lombok.Data;

@Data
public class OrderDTO {
  private String uuid;
  private OrderStatus status;

  public static OrderDTO of(Order order){
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setUuid(order.getUuid());
    orderDTO.setStatus(order.getStatus());
    return orderDTO;
  }
}
