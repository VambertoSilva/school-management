package com.vamberto.School.repositories;

import com.vamberto.School.models.Reservation;
import com.vamberto.School.models.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository  extends JpaRepository<Reservation, UUID> {
Optional<Reservation> findByBookIdAndStatus(UUID bookId, ReservationStatus status);
}
