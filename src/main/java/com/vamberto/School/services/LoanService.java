package com.vamberto.School.services;

import com.vamberto.School.dtos.LoanDTO;
import com.vamberto.School.models.Loan;
import com.vamberto.School.models.enums.LoanStatus;
import com.vamberto.School.repositories.BookRepository;
import com.vamberto.School.repositories.LoanRepository;
import com.vamberto.School.repositories.UsersRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Service
@RequestMapping
@Data
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;

    public Loan createLoan(LoanDTO dto){

        if(bookRepository.existsById(dto.bookId()) && usersRepository.existsById(dto.userId())){
            Loan newLoan = new Loan();
            newLoan.setLoanDate(LocalDateTime.now());
            newLoan.setStatus(LoanStatus.ACTIVE);
            newLoan.setUserId(dto.userId());
            newLoan.setBookId(dto.bookId());
            newLoan.setReturnDate(LocalDateTime.now().plusWeeks(1));

            return  loanRepository.save(newLoan);
        }else{
            throw new RuntimeException("Erro ao verificar os dados");
        }

    }
}
