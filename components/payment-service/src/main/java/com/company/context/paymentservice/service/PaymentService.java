package com.company.context.paymentservice.service;

import com.company.context.paymentservice.data.Payment;
import com.company.context.paymentservice.data.PaymentRepository;
import com.company.context.paymentservice.data.PaymentStatus;
import com.company.context.paymentservice.domain.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

  private final PaymentRepository paymentRepository;

  @Autowired
  public PaymentService(final PaymentRepository paymentRepository){
    this.paymentRepository = paymentRepository;
  }

  public PaymentDTO createPayment(Payment payment){
    payment = paymentRepository.saveAndFlush(payment);
    return PaymentDTO.of(payment);
  }

  public PaymentDTO updatePaymentStatus(String uuid, PaymentStatus paymentStatus){
    Optional<Payment> payment = paymentRepository.findByUuid(uuid);
    if(!payment.isPresent()){
      return null;
    }
    Payment paymentToUpdate = payment.get();
    paymentToUpdate.setStatus(paymentStatus);
    return PaymentDTO.of(paymentRepository.saveAndFlush(paymentToUpdate));
  }

  public PaymentDTO findPaymentByUuid(String uuid){
    Optional<Payment> payment = paymentRepository.findByUuid(uuid);
    if(!payment.isPresent()){
      return null;
    }
    return PaymentDTO.of(payment.get());
  }

  public PaymentDTO findPaymentByOrderUuid(String orderUuid){
    Optional<Payment> payment = paymentRepository.findByOrderUuid(orderUuid);
    if(!payment.isPresent()){
      return null;
    }
    return PaymentDTO.of(payment.get());
  }
}
