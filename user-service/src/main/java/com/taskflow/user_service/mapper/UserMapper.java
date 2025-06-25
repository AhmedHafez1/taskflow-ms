package com.taskflow.user_service.mapper;

import com.taskflow.user_service.dto.RegisterRequest;
import com.taskflow.user_service.dto.UserResponse;
import com.taskflow.user_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
    UserResponse toUserResponse(User user);
}
