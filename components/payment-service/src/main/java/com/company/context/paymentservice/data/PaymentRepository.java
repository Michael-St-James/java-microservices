package com.company.context.paymentservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
  Optional<Payment> findByUuid(String uuid);
  Optional<Payment> findByOrderUuid(String uuid);
}
