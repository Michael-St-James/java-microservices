package com.company.context.paymentservice.data;

import com.company.context.paymentservice.data.PaymentStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "payments")
public class Payment {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PaymentDetail> paymentDetails = new ArrayList<>();

  @Column(name = "uuid")
  private String uuid;

  @Column(name = "order_uuid")
  private String orderUuid;

  @Enumerated(EnumType.STRING)
  @Column(name = "status",
      columnDefinition = "ENUM('SUBMITTED', 'PENDING', 'COMPLETED', 'FAILED')")
  private PaymentStatus status;
}