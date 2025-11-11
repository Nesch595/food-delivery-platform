package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto register(UserDto userDto, String rawPassword) {
        if (userRepository.existsByEmail(userDto.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = userMapper.toEntity(userDto);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setRoles(
                Set.of(roleRepository.findByName("Customer")
                        .orElseThrow(() -> new RuntimeException("Default role not found")))
        );

        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto getProfile(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public UserDto updateProfile(Long id, UserDto updated) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        User updatedUser = userMapper.toEntity(updated);
        if (updated.fullName() != null && !updated.fullName().equals(user.getFullName())) {
            user.setFullName(updated.fullName());
        }
        if (updated.email() != null && !updated.email().equals(user.getEmail())) {
            user.setEmail(updated.email());
        }
        if (updated.addresses() != null && !updated.addresses().equals(user.getAddresses())) {
            user.setAddresses(updatedUser.getAddresses());
        }
        if (updatedUser.getPasswordHash() != null && !updatedUser.getPasswordHash().equals(user.getPasswordHash())) {
            user.setPasswordHash(passwordEncoder.encode(updatedUser.getPasswordHash()));
        }

        if (updated.roles() != null && !updated.roles().equals(user.getRoles())) {
            user.setRoles(updatedUser.getRoles());
        }

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

}
