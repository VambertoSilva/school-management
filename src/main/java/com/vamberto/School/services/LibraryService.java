package com.vamberto.School.services;

import com.vamberto.School.models.Classification;
import com.vamberto.School.repositories.BookRepository;
import com.vamberto.School.repositories.ClassificationRepository;
import com.vamberto.School.repositories.LoanRepository;
import com.vamberto.School.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final ClassificationRepository classificationRepository;
    private final ReservationRepository reservationRepository;



    public Classification createClassification(Classification classification){

            return  classificationRepository.save(classification);
    }

    public void removeClassification(UUID id){
        classificationRepository.deleteById(id);
    }

    public List<Classification> listClassification(){
       return classificationRepository.findAll();
    }


}
