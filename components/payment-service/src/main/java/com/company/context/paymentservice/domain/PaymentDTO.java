package com.company.context.paymentservice.domain;

import com.company.context.paymentservice.data.Payment;
import com.company.context.paymentservice.data.PaymentDetail;
import com.company.context.paymentservice.data.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonRootName("payment")
public class PaymentDTO {

  @JsonProperty("uuid")
  private String uuid;

  @JsonProperty("order_uuid")
  private String orderUuid;

  @JsonProperty("status")
  private PaymentStatus status;

  @JsonProperty("payment_details")
  private List<PaymentDetailDTO> paymentDetails;

  public static PaymentDTO of(Payment payment){
    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setUuid(payment.getUuid());
    paymentDTO.setUuid(payment.getOrderUuid());
    paymentDTO.setStatus(payment.getStatus());
    paymentDTO.setPaymentDetails(payment.getPaymentDetails().stream().map(x -> PaymentDetailDTO.of(x)).collect(Collectors.toList()));
    return paymentDTO;
  }
}
