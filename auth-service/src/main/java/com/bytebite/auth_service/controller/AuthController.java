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

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request.getEmail(), request.getPassword());
        return "Successful registration!!!";
    }

    @Data
    static class RegisterRequest {
        private String email;
        private String password;
    }
}
