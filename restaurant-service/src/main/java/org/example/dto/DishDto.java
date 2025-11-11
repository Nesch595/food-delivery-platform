package org.example.dto;

public record DishDto(
        Long id,
        String name,
        String description,
        double price,
        String imageUrl,
        Long restaurantId
) {}
