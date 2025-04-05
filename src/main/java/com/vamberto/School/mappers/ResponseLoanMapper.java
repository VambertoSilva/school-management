package com.vamberto.School.mappers;

import com.vamberto.School.dtos.ResponseLoanDTO;
import com.vamberto.School.models.Loan;

public class ResponseLoanMapper {
    public static ResponseLoanDTO toDTO(Loan loan, String name, String title ){

        return  new ResponseLoanDTO(loan.getId(),loan.getLoanDate(), loan.getDueDate(),loan.getReturnDate(),loan.getFineAmount(), name, title);
    }
}
