package com.taskflow.user_service.service;

import com.taskflow.user_service.dto.AuthResponse;
import com.taskflow.user_service.dto.LoginRequest;
import com.taskflow.user_service.dto.RegisterRequest;
import com.taskflow.user_service.dto.UserResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse getCurrentUser(Authentication authentication);
    void logout(Authentication authentication);
}
