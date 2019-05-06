package com.company.context.paymentservice.service;

import com.company.context.paymentservice.data.*;
import com.company.context.paymentservice.domain.PaymentDTO;
import com.company.context.paymentservice.domain.PaymentDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentDetailService {

  private final PaymentDetailRepository paymentDetailRepository;

  @Autowired
  public PaymentDetailService(final PaymentDetailRepository paymentDetailRepository){
    this.paymentDetailRepository = paymentDetailRepository;
  }

  public PaymentDetailDTO createPaymentDetail(PaymentDetail paymentDetail){
    paymentDetail = paymentDetailRepository.saveAndFlush(paymentDetail);
    return PaymentDetailDTO.of(paymentDetail);
  }
//
//  public PaymentDetailDTO findPaymentByOrderUuid(String orderUuid){
//    Optional<Payment> payment = paymentDetailRepositoryRepository.findBy(orderUuid);
//    if(!payment.isPresent()){
//      return null;
//    }
//    return PaymentDTO.of(payment.get());
//  }
}
