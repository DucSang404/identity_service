package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.PermissionRequest;
import com.devteria.identity_service.dto.request.UserRequestDTO;
import com.devteria.identity_service.dto.response.PermissionResponse;
import com.devteria.identity_service.dto.response.UserResponseDTO;
import com.devteria.identity_service.entity.PermissionEntity;
import com.devteria.identity_service.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionEntity toPermissionEntity(PermissionRequest dto);
    PermissionResponse toPermissionRespone(PermissionEntity entity);
}
