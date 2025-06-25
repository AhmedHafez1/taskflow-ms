package com.taskflow.user_service.dto;

import com.taskflow.user_service.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @Email @NotBlank String email,
        @Size(min = 8, message = "Password must be at least 6 characters long") String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull Role role
) {
}
