package org.example.dto;

import java.util.List;

public record RestaurantDto(
        Long id,
        String name,
        String cuisine,
        String address,
        List<DishDto> dishes
) {}
