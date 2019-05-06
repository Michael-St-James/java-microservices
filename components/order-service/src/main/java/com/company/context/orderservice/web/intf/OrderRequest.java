package com.company.context.orderservice.web.intf;

import com.company.context.orderservice.data.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {
  @NotNull
  OrderStatus orderStatus;
}
