package com.taskflow.user_service.controller;

import com.taskflow.user_service.dto.UserResponse;
import com.taskflow.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@RequestHeader("X-Username") String username) {
        UserResponse userResponse = userService.getCurrentUser(username);
        return ResponseEntity.ok(userResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<UserResponse[]> getAllUsers() {
        UserResponse[] users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
