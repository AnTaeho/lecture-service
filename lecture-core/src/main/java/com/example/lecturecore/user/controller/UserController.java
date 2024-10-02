package com.example.lecturecore.user.controller;

import com.example.lecturecore.common.dto.CommonResponse;
import com.example.lecturecore.common.dto.EmptyDto;
import com.example.lecturecore.common.dto.TokenResponse;
import com.example.lecturecore.security.service.JwtService;
import com.example.lecturecore.user.controller.dto.JoinRequest;
import com.example.lecturecore.user.controller.dto.LoginRequest;
import com.example.lecturecore.user.controller.dto.UserEmailResponse;
import com.example.lecturecore.user.controller.dto.UserResponse;
import com.example.lecturecore.user.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final JwtService jwtService;

    @PostMapping
    public CommonResponse<UserResponse> join(@RequestBody JoinRequest joinRequest) {
        return new CommonResponse<>(userCommandService.join(joinRequest));
    }

    @PostMapping("/login")
    public CommonResponse<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        UserEmailResponse email = userCommandService.login(loginRequest);
        return new CommonResponse<>(jwtService.toTokenResponse(email.value()));
    }

    @PostMapping("/logout")
    public CommonResponse<EmptyDto> logout() {
        userCommandService.logout();
        return CommonResponse.EMPTY;
    }

}
