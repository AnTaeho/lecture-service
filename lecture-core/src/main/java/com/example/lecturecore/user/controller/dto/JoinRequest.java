package com.example.lecturecore.user.controller.dto;

public record JoinRequest(
        String name,
        String email,
        String password
) {
}
