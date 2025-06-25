package com.taskflow.user_service.service;

import com.taskflow.user_service.entity.User;

public interface JwtService {
    String generateToken(User user);
}
