package org.example.service;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.security.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final Map<String, String> refreshStorage = new HashMap<>();

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userService.getByUsername(authRequest.getEmail());

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPasswordHash())) {
            throw new AuthException("Wrong password");
        }

        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);
        refreshStorage.put(user.getEmail(), refreshToken);

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse register(@NonNull JwtRequest registerRequest) throws AuthException {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new AuthException("User already exists");
        }

        User newUser = User.builder()
                .email(registerRequest.getEmail())
                .passwordHash(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Set.of(new Role(1L, "USER", null)))
                .build();

        userService.save(newUser);

        final String accessToken = jwtService.generateToken(newUser);
        final String refreshToken = jwtService.generateRefreshToken(newUser);
        refreshStorage.put(newUser.getEmail(), refreshToken);

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (!jwtProvider.validateRefreshToken(refreshToken)) {
            return new JwtResponse(null, null);
        }

        final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
        final String email = claims.getSubject();
        final String savedRefreshToken = refreshStorage.get(email);

        if (savedRefreshToken == null || !savedRefreshToken.equals(refreshToken)) {
            return new JwtResponse(null, null);
        }

        final User user = userService.getByUsername(email);
        final String accessToken = jwtProvider.generateAccessToken(user);

        return new JwtResponse(accessToken, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String savedRefreshToken = refreshStorage.get(email);

            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final User user = userService.getByUsername(email);
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);

                refreshStorage.put(email, newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
