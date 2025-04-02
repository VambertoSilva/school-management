package com.vamberto.School.repositories;

import com.vamberto.School.models.Loan;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LoanRepository  extends JpaRepository<Loan, UUID> {
    List<Loan>  findByReturnDate(LocalDate date);
}
