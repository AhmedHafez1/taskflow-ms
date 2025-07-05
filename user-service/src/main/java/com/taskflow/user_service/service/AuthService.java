package com.taskflow.user_service.service;

import com.taskflow.user_service.dto.AuthResponse;
import com.taskflow.user_service.dto.LoginRequest;
import com.taskflow.user_service.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
