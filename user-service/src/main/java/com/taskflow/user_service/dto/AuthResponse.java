package com.taskflow.user_service.dto;


public record AuthResponse(
        String token,
        UserResponse user
) {
}
