package com.bytebite.auth_service.controller;

import com.bytebite.auth_service.service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/home")
    public String auth_home() {
        return "Welcome to Auth Service!!!";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request.getEmail(), request.getPassword());
        return "Successful registration!!!";
    }

    @PostMapping("/login")
    public String login(@RequestBody RegisterRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }

    @Data
    static class RegisterRequest {
        private String email;
        private String password;
    }
}
