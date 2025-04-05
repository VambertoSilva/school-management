package com.vamberto.School.models;

import com.vamberto.School.models.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(nullable = false, updatable = false)
    private UUID userId;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String title;

    @JoinColumn(nullable = false, updatable = false)
    private UUID bookId;

    @JoinColumn(nullable = false, updatable = false)
    private LocalDateTime loanDate;

    @JoinColumn(nullable = false)
    private LocalDateTime dueDate;

    private LocalDate returnDate;

    @Column(nullable = false)
    private double fineAmount;

    @JoinColumn(nullable = false)
    private LoanStatus status;
}
