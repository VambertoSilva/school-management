package com.vamberto.School.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsersCreateStudentDTO(
        @NotBlank @Size(min = 3, max = 50)
        String username,
        @NotBlank @Email
        String email,
        @NotBlank @Size(min = 3, max = 100)
        String name,
        @NotBlank @Size(min = 6)
        String password
) {}