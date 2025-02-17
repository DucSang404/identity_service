package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.*;
import com.devteria.identity_service.dto.response.AuthResponseDTO;
import com.devteria.identity_service.dto.response.IntrospectResponse;
import com.devteria.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/login")
    ApiResponse<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO requestDTO) throws JOSEException {
        var result = authenticationService.authenticate(requestDTO);
        return ApiResponse.<AuthResponseDTO>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest requestDTO) throws JOSEException, ParseException {
        var result = authenticationService.introspect(requestDTO);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest requestDTO) throws JOSEException, ParseException {
        authenticationService.logout(requestDTO);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthResponseDTO> authenticate(@RequestBody RefreshRequest requestDTO) throws JOSEException, ParseException {
        var result = authenticationService.refreshToken(requestDTO);
        return ApiResponse.<AuthResponseDTO>builder()
                .result(result)
                .build();
    }
}
