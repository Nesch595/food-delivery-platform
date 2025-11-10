package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "total_price", nullable = false)
    private double totalPrice = 0.00;
}
