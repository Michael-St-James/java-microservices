package com.company.context.orderservice.web;

import com.company.context.orderservice.domain.OrderDTO;
import com.company.context.orderservice.domain.PingDTO;
import com.company.context.orderservice.manager.OrderManager;
import com.company.context.orderservice.web.intf.OrderRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/context/v1/order/")
public class OrderController {

  private static final Logger log = LogManager.getLogger(OrderController.class);

  private final OrderManager orderManager;

  @Autowired
  public OrderController(final OrderManager orderManager){
    this.orderManager = orderManager;
  }

  @GetMapping("/ping")
  public ResponseEntity ping(HttpServletRequest request){
    log.info("message=\"order-service ping request received\" request=\"{}\"", request.getRemoteAddr());
    return ResponseEntity.ok(new PingDTO("Successfully pinged order service"));
  }

  @GetMapping(value = {"/{uuid}"})
  public ResponseEntity getOrder(@PathVariable String uuid){
    log.info("message=\"order-service GET request received\" uuid=\"{}\"", uuid);
    OrderDTO orderDTO = orderManager.getOrder(uuid);
    if(orderDTO == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    log.info("message=\"order-service successfully retrieved order\" order=\"{}\"", orderDTO);
    return ResponseEntity.ok(orderDTO);
  }

  @PostMapping
  public ResponseEntity newOrder(@RequestBody @Valid OrderRequest orderRequest){
    log.info("message=\"order-service POST request received\" request=\"{}\"", orderRequest);
    OrderDTO orderDTO = orderManager.newOrder(orderRequest.getOrderStatus());
    log.info("message=\"order-service successfully created order\" order=\"{}\"", orderDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
  }

  @PutMapping(value = {"/{uuid}"})
  public ResponseEntity updateOrder(@PathVariable String uuid, @RequestBody @Valid OrderRequest orderRequest){
    log.info("message=\"order-service PUT request received\" uuid=\"{}\" request=\"{}\"", uuid, orderRequest);
    OrderDTO orderDTO = orderManager.updateOrderStatus(uuid, orderRequest.getOrderStatus());
    if(orderDTO == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    log.info("message=\"order-service successfully updated order\" order=\"{}\"", orderDTO);
    return ResponseEntity.accepted().body(orderDTO);
  }
}
