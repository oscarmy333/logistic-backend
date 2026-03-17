package com.marvisa.logistic.security.mapper;

import com.marvisa.logistic.security.dto.UserMeResponse;
import com.marvisa.logistic.security.dto.UserResponse;
import com.marvisa.logistic.security.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(r -> r.getRol().name()).collect(java.util.stream.Collectors.toSet()))")
    UserResponse toResponse(User user);

    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(r -> r.getRol().name()).collect(java.util.stream.Collectors.toSet()))")
    UserMeResponse toMeResponse(User user);
}