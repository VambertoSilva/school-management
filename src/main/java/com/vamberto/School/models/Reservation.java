package com.vamberto.School.models;


import com.vamberto.School.models.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "book_copy")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(nullable = false, updatable = false)
    private UUID userId;

    @JoinColumn(nullable = false, updatable = false)
    private UUID bookId;

    @JoinColumn(nullable = false, updatable = false)
    private LocalDateTime reservationDate;

    private LocalDateTime reservationDueDate;

    @JoinColumn(nullable = false, updatable = false)
    private ReservationStatus reservationStatus;

    @JoinColumn(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(nullable = false, updatable = false)
    private LocalDateTime UpdatedAt;

    @JoinColumn(updatable = false, nullable = false)
    private UUID createdBy;

    @JoinColumn(nullable = false, updatable = false)
    private UUID updatedBy;
}
