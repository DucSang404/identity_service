package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.UserRequestDTO;
import com.devteria.identity_service.dto.response.UserResponseDTO;
import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean //dung de gia lap ma khong muon goi den repository thuc te
    private UserRepository userRepository;

    private UserRequestDTO requestDTO;
    private UserResponseDTO userResponseDTO;
    private UserEntity userEntity;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);

        requestDTO = UserRequestDTO.builder()
                .username("sang12")
                .firstName("Duc")
                .lastName("Sang")
                .password("12341234")
                .dob(dob)
                .build();

        userResponseDTO = UserResponseDTO.builder()
                .id(1L)
                .username("sang12")
                .firstName("Duc")
                .lastName("Sang")
                .dob(dob)
                .build();

        userEntity = UserEntity.builder()
                .id(1L)
                .username("sang12")
                .firstName("Duc")
                .lastName("Sang")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(userEntity);

        var response = userService.createUser(requestDTO);
        Assertions.assertThat(response.getId()).isEqualTo(1L);
        Assertions.assertThat(response.getUsername()).isEqualTo("sang12");

    }

    @Test
    void createUser_userExisted_fail() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        var exception = assertThrows(AppException.class,
                () -> userService.createUser(requestDTO));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);

    }

}
