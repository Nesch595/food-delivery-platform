package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "amount", nullable = false)
    private int amount = 1;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
