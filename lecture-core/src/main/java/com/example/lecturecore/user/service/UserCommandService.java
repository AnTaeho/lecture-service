package com.example.lecturecore.user.service;

import com.example.lecturecore.user.controller.dto.JoinRequest;
import com.example.lecturecore.user.controller.dto.LoginRequest;
import com.example.lecturecore.user.controller.dto.UserEmailResponse;
import com.example.lecturecore.user.controller.dto.UserResponse;

public interface UserCommandService {

    UserResponse join(JoinRequest joinRequest);
    UserEmailResponse login(LoginRequest loginRequest);
    void logout();

}
