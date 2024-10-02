package com.example.lecturecore.user.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record LoginRequest(
        String email,
        String password
) {

    public Authentication toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
