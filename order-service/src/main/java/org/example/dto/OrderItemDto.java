package org.example.dto;

public record OrderItemDto(
        Long id,
        Long dishId,
        int quantity,
        double price,
        Long orderId
) {}
