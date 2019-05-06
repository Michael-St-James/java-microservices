package com.company.context.orderservice.domain;

import lombok.Data;

@Data
public class PingDTO {

  String message;

  public PingDTO(String message){
    this.message=message;
  }

}
