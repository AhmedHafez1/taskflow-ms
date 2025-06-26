package com.taskflow.user_service.service.impl;

import com.taskflow.common.exception.*;
import com.taskflow.user_service.dto.AuthResponse;
import com.taskflow.user_service.dto.LoginRequest;
import com.taskflow.user_service.dto.RegisterRequest;
import com.taskflow.user_service.entity.User;
import com.taskflow.user_service.mapper.UserMapper;
import com.taskflow.user_service.repository.UserRepository;
import com.taskflow.user_service.service.AuthService;
import com.taskflow.user_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.email()))
            throw new AlreadyExistsException("Email already exists!", "User");

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.password()));

        userRepo.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, userMapper.toUserResponse(user));
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepo.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException("Email does not exists!"));

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new InvalidCredentialsException("Invalid password!");

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, userMapper.toUserResponse(user));
    }
}
