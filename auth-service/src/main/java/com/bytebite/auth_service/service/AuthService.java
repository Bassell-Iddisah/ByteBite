package com.bytebite.auth_service.service;

import com.bytebite.auth_service.model.User;
import com.bytebite.auth_service.repository.UserRepository;
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

    public void register(String email, String rawPassword) {
        String hashed = passwordEncoder.encode(rawPassword);
        User user = User.builder()
                .email(email)
                .password(hashed)
                .roles(Collections.singleton("ROLE_CUSTOMER"))
                .build();
        userRepository.save(user);
    }
}
