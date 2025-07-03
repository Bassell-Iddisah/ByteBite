package com.bytebite.auth_service.service;

import com.bytebite.auth_service.model.User;
import com.bytebite.auth_service.repository.UserRepository;
import com.bytebite.auth_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(String email, String rawPassword) {
        String hashed = passwordEncoder.encode(rawPassword);
        User user = User.builder()
                .email(email)
                .password(hashed)
                .roles(Collections.singleton("ROLE_CUSTOMER"))
                .build();
        userRepository.save(user);
    }

    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword()))
            throw new RuntimeException("Invalid Credentials");

        return jwtService.generateToken(user.getEmail(), user.getRoles());
    }
}
