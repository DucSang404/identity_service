package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.response.UserResponseDTO;
import com.devteria.identity_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Operations pertaining to user management")
public class UserSwaggerController {

    @Autowired
    private UserService userService;

    @Operation(summary = "View a list of available users", description = "Retrieves all users from the system", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "Unauthorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Forbidden from accessing the resource"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @GetMapping("/")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getUsers();
    }

    // Các phương thức khác
}
