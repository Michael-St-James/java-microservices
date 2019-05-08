package com.company.context.orderservice.manager;

import com.company.context.orderservice.data.Order;
import com.company.context.orderservice.data.OrderStatus;
import com.company.context.orderservice.domain.OrderDTO;
import com.company.context.orderservice.service.OrderService;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class OrderManagerTests {

  @InjectMocks
  OrderManager orderManager;

  @Mock
  OrderService orderService;

  @BeforeEach
  void beforeEachTest(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("New order - Positive")
  public void newOrderPostive(){
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setUuid(UUID.randomUUID().toString());
    orderDTO.setStatus(OrderStatus.SUBMITTED);
    Mockito.when(orderService.createOrder(Mockito.any(Order.class))).thenReturn(orderDTO);
    OrderDTO result = orderManager.newOrder(OrderStatus.SUBMITTED);
    Assertions.assertEquals(orderDTO, result);
  }

  @Test
  @DisplayName("Get order - Positive")
  void getOrderPositive(){
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setUuid(UUID.randomUUID().toString());
    orderDTO.setStatus(OrderStatus.SUBMITTED);
    Mockito.when(orderService.findOrderByUUID(Mockito.anyString())).thenReturn(orderDTO);
    OrderDTO result = orderManager.getOrder("u-u-i-d");
    Assertions.assertEquals(orderDTO, result);
  }

  @Test
  @DisplayName("Update order status - Positive")
  void updateStatusPositive(){
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setUuid(UUID.randomUUID().toString());
    orderDTO.setStatus(OrderStatus.SUBMITTED);
    Mockito.when(orderService.updateOrderStatus(Mockito.anyString(), Mockito.any(OrderStatus.class))).thenReturn(orderDTO);
    OrderDTO result = orderManager.updateOrderStatus("u-u-i-d",OrderStatus.COMPLETED);
    Assertions.assertEquals(orderDTO.getStatus(), result.getStatus());
  }

  @Test
  @Tag("gradle-skip")
  @DisplayName("Fails if not skipped!")
  void failsEveryTime(){
    Assertions.fail("This is supposed to fail, but this should have been skipped because of the tag");
  }

}
