package com.example.lecturecore.event.refreshtoken;

public record RefreshTokenEvent(
        String email,
        String refreshToken
) {
}
