package org.example.repository;

import org.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByStatus(String status);
    Optional<Order> findByOrderDate(LocalDateTime orderDate);
    Optional<Order> findByUserId(Long userId);
    Optional<Order> findByRestaurantId(Long restaurantId);
    Optional<Order> findByTotalPrice(double totalPrice);

    boolean existsByStatus(String status);
    boolean existsByOrderDate(LocalDateTime orderDate);
    boolean existsByUserId(Long userId);
    boolean existsByRestaurantId(Long restaurantId);
    boolean existsByTotalPrice(double totalPrice);
}
