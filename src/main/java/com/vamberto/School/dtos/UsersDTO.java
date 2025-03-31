package com.vamberto.School.dtos;

import com.vamberto.School.models.enums.UsersRole;
import jakarta.validation.constraints.Email;

import java.util.UUID;

public record UsersDTO(
        UUID id,
        String username,
        String email,
        String name,
        UsersRole usersRole
) {}
