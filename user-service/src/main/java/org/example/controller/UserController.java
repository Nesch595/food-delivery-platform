package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.security.JwtAuthentication;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users/me")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("users/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        JwtAuthentication authInfo = authService.getAuthInfo();
        UserDto userDto = userService.getProfile(Long.valueOf(authInfo.getUsername()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/users/me")
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto userDto) {
        JwtAuthentication authInfo = authService.getAuthInfo();
        UserDto updated = userService.updateProfile(Long.valueOf(authInfo.getUsername()), userDto);
        return ResponseEntity.ok(updated);
    }

}
