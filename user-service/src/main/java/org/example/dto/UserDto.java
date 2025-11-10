package org.example.dto;

import java.util.List;
import java.util.Set;

public record UserDto(
        Long id,
        String email,
        String fullName,
        List<AddressDto> addresses,
        Set<RoleDto> roles
) {}
