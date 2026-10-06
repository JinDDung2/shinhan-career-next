package com.example.springsecurity.memeber.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequestDto(
        @NotBlank String memId,
        @NotBlank String memNm,
        @Size(min = 4) String password
) {}
