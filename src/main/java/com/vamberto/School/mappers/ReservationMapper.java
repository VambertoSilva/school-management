package com.vamberto.School.mappers;

import com.vamberto.School.dtos.ReservationDTO;
import com.vamberto.School.models.Reservation;

public class ReservationMapper {

    public ReservationDTO toDTO(Reservation reservation){
        return new ReservationDTO(reservation.getUserId(), reservation.getBookId());
    }
}
