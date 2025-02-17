package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.ApiResponse;
import com.devteria.identity_service.dto.request.UserRequestDTO;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponseDTO;
import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO requestDTO) {
        log.info("Controller create user");
        ApiResponse<UserResponseDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(requestDTO));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<UserResponseDTO>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("username" + authentication.getName());
        for(GrantedAuthority s : authentication.getAuthorities()) {
            System.out.println(s);
        }
        return ApiResponse.<List<UserResponseDTO>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponseDTO> getUser(@PathVariable("userId") Long userId) {
        return ApiResponse.<UserResponseDTO>builder()
                        .result(userService.getUser(userId))
                        .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponseDTO> getMyInfo() {
        return ApiResponse.<UserResponseDTO>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponseDTO> updateUser(@PathVariable Long userId,
                                                   @RequestBody UserUpdateRequest requestDTO) {
        return ApiResponse.<UserResponseDTO>builder()
                .result(userService.updateUser(userId, requestDTO))
                .build();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
