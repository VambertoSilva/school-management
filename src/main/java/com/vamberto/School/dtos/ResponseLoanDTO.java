package com.vamberto.School.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseLoanDTO(
        UUID id,
        LocalDateTime loanDate,
        LocalDateTime dueDate,
        LocalDate returnDate,
        Double finaAmount,
        String bookTitle,
        String name

) {

}
