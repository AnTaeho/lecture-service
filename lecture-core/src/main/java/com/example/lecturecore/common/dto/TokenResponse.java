package com.example.lecturecore.common.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
