package com.vamberto.School.services;

import com.vamberto.School.configs.CustomUserDetails;
import com.vamberto.School.dtos.ReservationDTO;
import com.vamberto.School.models.Book;
import com.vamberto.School.models.Reservation;
import com.vamberto.School.models.enums.BookStatus;
import com.vamberto.School.models.enums.ReservationStatus;
import com.vamberto.School.repositories.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final UsersRepository usersRepository;
    private final ReservationRepository reservationRepository;

    public Reservation createReservation(ReservationDTO dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID id = userDetails.getId();

        if (!usersRepository.existsById(dto.userId())) {
            throw new IllegalArgumentException("Usuario não existe");

        }

        if (!bookRepository.existsById(dto.bookId())) {
            throw new IllegalArgumentException("Livro não existe");

        }



        Reservation reservation = new Reservation();


        reservation.setBookId(dto.bookId());
        reservation.setUserId(dto.userId());
        reservation.setReservationDate(LocalDateTime.now());

        Book book = bookRepository.findById(dto.bookId()).get();
        if (book.getStatus().equals(BookStatus.AVAILABLE)){

            reservation.setStatus(ReservationStatus.CONFIRMED);
            book.setStatus(BookStatus.RESERVED);
            bookRepository.save(book);

            return reservationRepository.save(reservation);
        }else if(book.getStatus().equals(BookStatus.RESERVED)){
            throw new IllegalArgumentException("Livro ja reservado");
        }


        reservation.setStatus(ReservationStatus.PENDING);

        return reservationRepository.save(reservation);


    } // criar mecanismo para concluir a reserva


    public void reservation(UUID bookId){

        Optional<Reservation> reservationOptional = reservationRepository.findByBookIdAndStatus(bookId, ReservationStatus.PENDING);

        if(!reservationOptional.isPresent()){
           return;
        }

        Reservation reservation = reservationOptional.get();
        reservation.setReservationDueDate(LocalDateTime.now());
        System.out.println("antes do confirmar: " + reservation.getStatus());
        reservation.setStatus(ReservationStatus.CONFIRMED);
        System.out.println("depois do confirmar: " + reservation.getStatus());
        reservationRepository.save(reservation);
        System.out.println("Livro reservado: " + reservationRepository.save(reservation));

    }

    public void cancel(UUID id ){
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if(!reservationOptional.isPresent()){
            throw new IllegalArgumentException("Id invalido ou reserva nao existe");
        }

        Reservation reservation = reservationOptional.get();
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    public void delete (UUID id){
        reservationRepository.deleteById(id);
    }
}
