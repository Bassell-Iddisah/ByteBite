package com.bytebite.auth_service.config;


import com.bytebite.auth_service.model.User;
import com.bytebite.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if(userRepository.findByEmail("admin@bytebites.com").isEmpty()) {
            User admin = User.builder()
                    .email("admin@bytebites.com")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(Set.of("ROLE_ADMIN", "ROLE_RESTAURANT_OWNER"))
                    .build();

            userRepository.save(admin);
            System.out.println("Admin user seeded successfully!");
        } else {
            System.out.println("Admin user already exists!");
        }
    }
}
