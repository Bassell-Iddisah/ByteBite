package com.bytebite.auth_service.security;

import com.bytebite.auth_service.security.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");

        String jwt = jwtService.generateToken(email, Collections.singleton("ROLE_CUSTOMER"));

        // Return JWT as JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"accessToken\": \"" + jwt + "\", \"roles\": [\"ROLE_CUSTOMER\"]}");
    }
}
