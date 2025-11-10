package org.example.repository;

import org.example.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByDishId(Long dishId);
    Optional<OrderItem> findByQuantity(int quantity);
    Optional<OrderItem> findByPrice(double price);
    Optional<OrderItem> findByOrderId(Long orderId);

    boolean existsByDishId(Long dishId);
    boolean existsByQuantity(int quantity);
    boolean existsByPrice(double price);
    boolean existsByOrderId(Long orderId);
}
