package com.example.lecturecore.user.service;

import com.example.lecturecore.user.controller.dto.JoinRequest;
import com.example.lecturecore.user.controller.dto.LoginRequest;
import com.example.lecturecore.user.controller.dto.UserEmailResponse;
import com.example.lecturecore.user.controller.dto.UserResponse;
import com.example.lecturecore.user.domain.User;
import com.example.lecturecore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse join(JoinRequest joinRequest) {
        checkEmail(joinRequest);
        User user = new User(
                joinRequest.name(),
                joinRequest.email(),
                joinRequest.password()
        );

        User savedUser = userRepository.save(user);
        savedUser.encodePassword(passwordEncoder);

        return new UserResponse(savedUser.getId());
    }

    private void checkEmail(JoinRequest joinRequest) {
        if (userRepository.existsByEmail(joinRequest.email())) {
            throw new IllegalArgumentException("해당 이메일은 이미 존재합니다.");
        }
    }
    @Override
    public UserEmailResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(loginRequest.toAuthenticationToken());
        return new UserEmailResponse(authentication.getName());
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
