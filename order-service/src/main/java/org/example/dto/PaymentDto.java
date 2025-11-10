package org.example.dto;

public record PaymentDto(
        Long id,
        String method,
        int amount,
        String status,
        Long orderId
) {}
