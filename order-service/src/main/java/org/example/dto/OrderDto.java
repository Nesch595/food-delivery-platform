package org.example.dto;

import java.time.LocalDateTime;

public record OrderDto(
        Long id,
        String status,
        LocalDateTime orderDate,
        Long userId,
        Long restaurantId,
        double totalPrice
) {}