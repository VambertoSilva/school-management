package com.vamberto.School.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ReservationDTO(
        @NotBlank(message = "userId invalido")
        UUID userId,
        @NotBlank(message = "BookId invalido")
        UUID bookId) {
}
