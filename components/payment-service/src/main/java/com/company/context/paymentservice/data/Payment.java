package com.company.context.paymentservice.data;

import com.company.context.paymentservice.data.PaymentStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

  @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<PaymentDetail> paymentDetails;

  @NotNull
  @Column(name = "uuid")
  private String uuid;

  @NotNull
  @Column(name = "order_uuid")
  private String orderUuid;

  @Enumerated(EnumType.STRING)
  @Column(name = "status",
      columnDefinition = "ENUM('SUBMITTED', 'PENDING', 'COMPLETED', 'FAILED')")
  private PaymentStatus status;
}
