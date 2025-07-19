package com.taskflow.user_service.service;

import com.taskflow.user_service.dto.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse getCurrentUser(String email);
    UserResponse[] getAllUsers();
    UserResponse getUserById(UUID id);
}
