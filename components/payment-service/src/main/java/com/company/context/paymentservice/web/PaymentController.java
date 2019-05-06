package com.company.context.paymentservice.web;

import com.company.context.paymentservice.domain.PaymentDTO;
import com.company.context.paymentservice.domain.PingDTO;
import com.company.context.paymentservice.manager.PaymentManager;
import com.company.context.paymentservice.web.intf.PaymentRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/context/v1/payment/")
public class PaymentController {

  private static final Logger log = LogManager.getLogger(PaymentController.class);

  private final PaymentManager paymentManager;

  @Autowired
  public PaymentController(final PaymentManager paymentManager){
    this.paymentManager = paymentManager;
  }

  @GetMapping("/ping")
  public ResponseEntity ping(HttpServletRequest request){
    log.info("message=\"payment-service ping request received\" request=\"{}\"", request.getRemoteAddr());
    return ResponseEntity.ok(new PingDTO("Successfully pinged payment-service"));
  }

  @GetMapping(value = {"/{uuid}", ""})
  public ResponseEntity getPayment(@PathVariable Optional<String> uuid,  @RequestParam(name = "order_uuid") Optional<String> orderUuid){
    PaymentDTO paymentDTO;
    if(uuid.isPresent()) {
      log.info("message=\"payment-service GET request received\" uuid=\"{}\"", uuid);
      paymentDTO = paymentManager.getPayment(uuid.get());
    } else if(orderUuid.isPresent()){
      log.info("message=\"payment-service GET request received\" uuid=\"{}\"", uuid);
      paymentDTO = paymentManager.getPaymentByOrderUuid(orderUuid.get());
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid parameters");
    }
    if(paymentDTO == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    log.info("message=\"payment-service successfully retrieved payment\" payment=\"{}\"", paymentDTO);
    return ResponseEntity.ok(paymentDTO);
  }

  @PostMapping
  public ResponseEntity newPayment(@RequestBody @Valid PaymentRequest paymentRequest){
    log.info("message=\"payment-service POST request received\" request=\"{}\"", paymentRequest);
    PaymentDTO paymentDTO = paymentManager.newPayment(paymentRequest);
    log.info("message=\"payment-service successfully created payment\" payment=\"{}\"", paymentDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(paymentDTO);
  }

  @PutMapping(value = {"/{uuid}"})
  public ResponseEntity updatePayment(@PathVariable String uuid, @RequestBody @Valid PaymentRequest paymentRequest){
    log.info("message=\"payment-service PUT request received\" uuid=\"{}\" request=\"{}\"", uuid, paymentRequest);
    PaymentDTO paymentDTO = paymentManager.updatePaymentStatus(uuid, paymentRequest.getPaymentStatus());
    if(paymentDTO == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    log.info("message=\"payment-service successfully updated payment\" payment=\"{}\"", paymentDTO);
    return ResponseEntity.accepted().body(paymentDTO);
  }
}
