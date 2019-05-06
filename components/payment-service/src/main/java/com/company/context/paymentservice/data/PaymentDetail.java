package com.company.context.paymentservice.data;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payment_details")
public class PaymentDetail {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id")
  private Payment payment;

  @Column(name = "total_amount")
  private BigDecimal totalAmount;

  @Column(name = "currency")
  private String currency;

}
