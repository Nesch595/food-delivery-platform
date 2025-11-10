package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish_id", nullable = false)
    private Long dishId;

    @Column(name = "quantity", nullable = false)
    private int quantity = 1;

    @Column(name = "price", nullable = false)
    private double price = 0.00;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}

