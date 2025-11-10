package org.example.dto;

import java.util.Set;

public record RoleDto(
        Long id,
        String name,
        Set<UserDto> users
) {}
