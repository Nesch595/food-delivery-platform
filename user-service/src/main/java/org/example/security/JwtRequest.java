package org.example.security;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.RoleDto;

import java.util.Set;

@Setter
@Getter
public class JwtRequest {
    private String email;
    private String fullName;
    private String password;
    private Set<RoleDto> roles;
}
