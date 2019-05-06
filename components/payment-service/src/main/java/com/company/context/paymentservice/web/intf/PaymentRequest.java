package com.company.context.paymentservice.web.intf;

import com.company.context.paymentservice.data.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentRequest {

  @NotNull
  @JsonProperty("order_uuid")
  String orderUuid;

  @NotNull
  @JsonProperty("status")
  PaymentStatus paymentStatus;

  @JsonProperty("amount")
  Double amount;

  @JsonProperty("currency")
  String currency;
}
