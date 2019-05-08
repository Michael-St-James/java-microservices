package com.company.context.orderservice.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "order", name = "orders")
public class Order {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid")
  private String uuid;

  @Enumerated(EnumType.STRING)
  @Column(name = "status",
      columnDefinition = "ENUM('SUBMITTED', 'PAYMENT_PENDING', 'PAYMENT_COMPLETED', 'SHIPPED', 'COMPLETED')")
  private OrderStatus status;
}
