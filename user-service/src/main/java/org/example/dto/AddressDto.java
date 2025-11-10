package org.example.dto;
import org.example.entity.User;

public record AddressDto(
        Long id,
        String street,
        String city,
        String zip,
        String state,
        String country,
        User user
) {}
