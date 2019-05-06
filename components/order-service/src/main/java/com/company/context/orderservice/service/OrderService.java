package com.company.context.orderservice.service;

import com.company.context.orderservice.data.OrderStatus;
import com.company.context.orderservice.data.Order;
import com.company.context.orderservice.data.OrderRepository;
import com.company.context.orderservice.domain.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(final OrderRepository orderRepository){
    this.orderRepository = orderRepository;
  }

  public OrderDTO createOrder(Order order){
    order = orderRepository.saveAndFlush(order);
    return OrderDTO.of(order);
  }

  public OrderDTO updateOrderStatus(String uuid, OrderStatus orderStatus){
    Optional<Order> order = orderRepository.findByUuid(uuid);
    if(!order.isPresent()){
      return null;
    }
    Order orderToUpdate = order.get();
    orderToUpdate.setStatus(orderStatus);
    return OrderDTO.of(orderRepository.saveAndFlush(orderToUpdate));
  }

  public OrderDTO findOrderByUUID(String uuid){
    Optional<Order> order = orderRepository.findByUuid(uuid);
    if(!order.isPresent()){
      return null;
    }
    return OrderDTO.of(order.get());
  }
}
