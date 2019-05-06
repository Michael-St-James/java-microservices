package com.company.context.orderservice;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {
  @NotNull
  OrderStatus orderStatus;
}
