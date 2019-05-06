package com.company.context.paymentservice.manager;

import com.company.context.paymentservice.data.Payment;
import com.company.context.paymentservice.data.PaymentDetail;
import com.company.context.paymentservice.data.PaymentStatus;
import com.company.context.paymentservice.domain.PaymentDTO;
import com.company.context.paymentservice.service.PaymentService;
import com.company.context.paymentservice.web.intf.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PaymentManager {

  private final PaymentService paymentService;

  @Autowired
  public PaymentManager(final PaymentService paymentService){
    this.paymentService = paymentService;
  }

  public PaymentDTO getPayment(String uuid){
    PaymentDTO paymentDTO = paymentService.findPaymentByUuid(uuid);
    return paymentDTO;
  }

  public PaymentDTO getPaymentByOrderUuid(String orderUuid){
    PaymentDTO paymentDTO = paymentService.findPaymentByOrderUuid(orderUuid);
    return paymentDTO;
  }

  public PaymentDTO newPayment(PaymentRequest paymentRequest){
    PaymentDetail paymentDetail = new PaymentDetail();
    paymentDetail.setCurrency(paymentRequest.getCurrency());
    paymentDetail.setTotalAmount(BigDecimal.valueOf(paymentRequest.getAmount()));

    List<PaymentDetail> paymentDetails = new ArrayList<>();
    paymentDetails.add(paymentDetail);

    Payment payment = new Payment();
    payment.setUuid(UUID.randomUUID().toString());
    payment.setOrderUuid(paymentRequest.getOrderUuid());
    payment.setStatus(paymentRequest.getPaymentStatus());
    payment.setPaymentDetails(paymentDetails);
    paymentDetail.setPayment(payment);

    PaymentDTO paymentDTO = paymentService.createPayment(payment);
    return paymentDTO;
  }

  public PaymentDTO updatePaymentStatus(String uuid, PaymentStatus orderStatus){
    PaymentDTO paymentDTO = paymentService.updatePaymentStatus(uuid, orderStatus);
    return paymentDTO;
  }
}
