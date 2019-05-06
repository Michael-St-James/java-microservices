package com.company.context.orderservice.manager;

import com.company.context.orderservice.data.Order;
import com.company.context.orderservice.data.OrderStatus;
import com.company.context.orderservice.domain.OrderDTO;
import com.company.context.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderManager {

  private final OrderService orderService;

  @Autowired
  public OrderManager(final OrderService orderService){
    this.orderService = orderService;
  }

  public OrderDTO getOrder(String uuid){
    OrderDTO orderDTO = orderService.findOrderByUUID(uuid);
    return orderDTO;
  }

  public OrderDTO newOrder(OrderStatus orderStatus){
    Order order = new Order();
    order.setUuid(UUID.randomUUID().toString());
    order.setStatus(orderStatus);
    OrderDTO orderDTO = orderService.createOrder(order);
    return orderDTO;
  }

  public OrderDTO updateOrderStatus(String uuid, OrderStatus orderStatus){
    OrderDTO orderDTO = orderService.updateOrderStatus(uuid, orderStatus);
    return orderDTO;
  }
}
