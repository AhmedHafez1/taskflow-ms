package com.taskflow.user_service.service.impl;

import com.taskflow.user_service.dto.UserResponse;
import com.taskflow.user_service.entity.User;
import com.taskflow.user_service.mapper.UserMapper;
import com.taskflow.user_service.repository.UserRepository;
import com.taskflow.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse getCurrentUser(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse[] getAllUsers() {
        return userRepo
                .findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toArray(UserResponse[]::new);
    }

    @Override
    public UserResponse getUserById(UUID id) {
        return userRepo.findById(id).map(userMapper::toUserResponse).orElse(null);
    }
}
