package com.vamberto.School.models;


import com.vamberto.School.models.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reservation")
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;
}
