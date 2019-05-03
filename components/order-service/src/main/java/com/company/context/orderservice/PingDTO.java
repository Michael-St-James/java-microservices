package com.company.context.orderservice;

import lombok.Data;

@Data
public class PingDTO {

  String message;

  PingDTO(String message){
    this.message=message;
  }

}
