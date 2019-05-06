package com.company.context.orderservice.web.intf;

import com.company.context.orderservice.data.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonRootName("order_request")
public class OrderRequest {

  @NotNull
  @JsonProperty("status")
  OrderStatus orderStatus;
}
