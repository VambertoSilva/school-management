package com.vamberto.School.repositories;

import com.vamberto.School.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository  extends JpaRepository<Loan, UUID> {
    List<Loan>  findByReturnDate(LocalDate date);
    @Query(value = "SELECT COUNT(*) FROM loan WHERE user_id = :userId AND status IN ('0','1')", nativeQuery = true)
    int countActiveOrOverdueLoans(@Param("userId") UUID userId);
}
