package org.example.repository;

import org.example.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByMethod(String method);
    Optional<Payment> findByAmount(int amount);
    Optional<Payment> findByStatus(String status);
    Optional<Payment> findByOrderId(Long orderId);

    boolean existsByMethod(String method);
    boolean existsByAmount(int amount);
    boolean existsByStatus(String status);
    boolean existsByOrderId(Long orderId);
}
