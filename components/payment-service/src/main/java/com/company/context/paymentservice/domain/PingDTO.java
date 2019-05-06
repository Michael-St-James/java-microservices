package com.company.context.paymentservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("ping")
public class PingDTO {

  @JsonProperty("message")
  String message;

  public PingDTO(String message){
    this.message=message;
  }

}
