package com.company.context.orderservice;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/context/v1/order/")
public class OrderController {

  private static final Logger log = LogManager.getLogger(OrderController.class);

  @GetMapping("/ping")
  public ResponseEntity ping(HttpServletRequest request){
    log.info("message=\"order-service ping request received\" request=\"{}\"", request.getRemoteAddr());
    return ResponseEntity.ok(new PingDTO("Successfully pinged order service"));
  }
}
