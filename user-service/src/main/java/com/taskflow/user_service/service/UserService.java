package com.taskflow.user_service.service;

import com.taskflow.user_service.dto.UserResponse;

public interface UserService {
    UserResponse getCurrentUser(String email);

    UserResponse[] getAllUsers();
}
