package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.UserRequestDTO;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponseDTO;
import com.devteria.identity_service.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    UserEntity toUser(UserRequestDTO dto);
    @Mapping(source = "firstName", target = "lastName")
    UserResponseDTO toUserResponse(UserEntity userEntity);
    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget UserEntity entity, UserUpdateRequest dto);
}
