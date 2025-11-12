package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        user.setRoles(Set.of(
                roleRepository.findByName("USER")
                        .orElseThrow(() -> new RuntimeException("Default role 'USER' not found"))
        ));

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

        if (updated.fullName() != null) user.setFullName(updated.fullName());
        if (updated.email() != null) user.setEmail(updated.email());
        if (updated.addresses() != null) user.setAddresses(userMapper.toEntity(updated).getAddresses());
        if (updated.roles() != null) user.setRoles(userMapper.toEntity(updated).getRoles());

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email exists");
        }
        return save(user);
    }

    public User getByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with such email not found"));
    }
}
