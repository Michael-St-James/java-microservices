package com.company.context.paymentservice.domain;

import com.company.context.paymentservice.data.PaymentDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("payment_detail")
public class PaymentDetailDTO {

  @JsonProperty("total_amount")
  Double totalAmount;

  @JsonProperty("currency")
  String currency;

  public static PaymentDetailDTO of(PaymentDetail paymentDetail){
    PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO();
    paymentDetailDTO.setCurrency(paymentDetail.getCurrency());
    paymentDetailDTO.setTotalAmount(paymentDetail.getTotalAmount().doubleValue());
    return paymentDetailDTO;
  }
}
