package com.vamberto.School.controllers;

import com.vamberto.School.dtos.ReservationDTO;
import com.vamberto.School.models.Reservation;
import com.vamberto.School.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'DIRECTOR','STUDENT')")
    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody ReservationDTO dto){

        try {
            Reservation response = reservationService.createReservation(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }


    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'DIRECTOR','STUDENT')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        reservationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
