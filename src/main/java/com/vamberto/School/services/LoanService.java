package com.vamberto.School.services;

import com.vamberto.School.Exception.BookNotFoundException;
import com.vamberto.School.Exception.LoanNotFoundException;
import com.vamberto.School.Exception.LoanRenewInvalidDate;
import com.vamberto.School.dtos.LoanDTO;
import com.vamberto.School.models.Book;
import com.vamberto.School.models.Config;
import com.vamberto.School.models.Loan;
import com.vamberto.School.models.enums.BookStatus;
import com.vamberto.School.models.enums.LoanStatus;
import com.vamberto.School.repositories.BookRepository;
import com.vamberto.School.repositories.ConfigRepository;
import com.vamberto.School.repositories.LoanRepository;
import com.vamberto.School.repositories.UsersRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequestMapping
@Data
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;
    private final ConfigRepository configRepository;
    private final ReservationService reservationService;

    public Loan createLoan(LoanDTO dto){
        Optional<Config> quantityBooks = configRepository.findById("quantity_books");
        Optional<Book> optionalBook = bookRepository.findById(dto.bookId());
        int valueConfig  = Integer.parseInt( quantityBooks.get().getValor());



        if (optionalBook.isPresent() && usersRepository.existsById(dto.userId())) {
            int valueUSer = loanRepository.countActiveOrOverdueLoans(dto.userId());
            Book book = optionalBook.get();

            if(book.getStatus() == BookStatus.CHECKED_OUT){
                throw new IllegalStateException("Livro ja emprestado");
            }

            if(valueConfig <= valueUSer  ){
                throw new IllegalStateException("Limite maximo de livros emprestados atinjido");
            }

            System.out.println("Numero de livros emprestados: " + valueUSer + "/" + valueConfig);


            Loan newLoan = new Loan();
            newLoan.setLoanDate(LocalDateTime.now());
            newLoan.setStatus(LoanStatus.ACTIVE);
            newLoan.setUserId(dto.userId());
            newLoan.setBookId(dto.bookId());
            //newLoan.setReturnDate(LocalDate.now().plusDays(-1)); //  date for test loan fine
            newLoan.setReturnDate(LocalDate.now().plusWeeks(1));



            book.setStatus(BookStatus.CHECKED_OUT);
            bookRepository.save(book);
            return loanRepository.save(newLoan);
        } else {
            throw new RuntimeException("Erro ao verificar os dados");
        }


    }

    public Loan returnLoan(String id){
        UUID uuidID = UUID.fromString(id);
        Optional<Loan> loanOptional = loanRepository.findById(uuidID);

        if(loanOptional.isPresent()){
            Loan loan = loanOptional.get();

            Optional<Book> bookOptional = bookRepository.findById(loan.getBookId());

             if(bookOptional.isPresent()){
                 Book book =  bookOptional.get();
                 book.setStatus(BookStatus.AVAILABLE);
                 bookRepository.save(book);
             }


            loan.setStatus(LoanStatus.RETURNED);
             reservationService.reservation(loan.getBookId());
            return loanRepository.save(loan);

        }else{
            throw new LoanNotFoundException("Loan not found");
        }


    }

    public List<Loan> listLoan(){
        return  loanRepository.findAll();
    }

    public void  renewingLoan(String id){

        UUID uuidId = UUID.fromString(id);
        Optional<Loan> loanOptional = loanRepository.findById(uuidId);

        if(loanOptional.isPresent()){
            Loan loan = loanOptional.get();

            if(loan.getReturnDate().equals(LocalDate.now())){
                loan.setReturnDate(LocalDate.now());
            }else{
                throw new LoanRenewInvalidDate("Invalid return date");
            }
        }else{
            throw new LoanNotFoundException("Invalid id");
        }
    }




    @Scheduled(cron = "00 00 00 * * *")
    public void verifyFine(){;

        List<Loan> list = loanRepository.findByReturnDate(LocalDate.now().plusDays(-1));
        Optional<Config> fineConfigOptional = configRepository.findById("library_fine");

        if(fineConfigOptional.isPresent()) {
            double valueFine = Double.parseDouble(fineConfigOptional.get().getValor());


            for (Loan item : list) {
                item.setStatus(LoanStatus.OVERDUE);
                item.setFineAmount(item.getFineAmount() + valueFine);;
                loanRepository.save(item);
            }
        }


    }

}
