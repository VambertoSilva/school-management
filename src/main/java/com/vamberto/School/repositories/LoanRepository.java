package com.vamberto.School.repositories;

import com.vamberto.School.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository  extends JpaRepository<Loan, UUID> {
}
