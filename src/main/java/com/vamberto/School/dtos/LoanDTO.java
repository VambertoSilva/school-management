package com.vamberto.School.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record LoanDTO(
        @NotBlank(message = "userId invalido")
        UUID userId,
        @NotBlank(message = "bookId invalido")
        UUID bookId
) {

}
