package com.taskflow.user_service.dto;

public record LoginRequest(
        String email,
        String password
) {
}
