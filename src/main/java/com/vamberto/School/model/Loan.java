package com.vamberto.School.model;

import com.vamberto.School.model.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(nullable = false, updatable = false)
    private UUID userId;

    @JoinColumn(nullable = false, updatable = false)
    private UUID bookId;

    @JoinColumn(nullable = false, updatable = false)
    private LocalDateTime loanDate;

    @JoinColumn(nullable = false)
    private LocalDateTime dueDate;

    private LocalDateTime returnDate;

    @Column(nullable = false)
    private double fineAmount;

    @JoinColumn(nullable = false)
    private LoanStatus status;

    @JoinColumn(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(nullable = false, updatable = false)
    private LocalDateTime UpdatedAt;

    @JoinColumn(updatable = false, nullable = false)
    private UUID createdBy;

    @JoinColumn(nullable = false, updatable = false)
    private UUID updatedBy;

}
