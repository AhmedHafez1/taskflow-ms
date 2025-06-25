package com.taskflow.user_service.dto;

import com.taskflow.user_service.entity.Role;

import java.util.UUID;

public record UserResponse(UUID id, String email, String firstName, String lastName, Role role) {}
