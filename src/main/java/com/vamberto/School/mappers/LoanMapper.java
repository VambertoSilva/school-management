package com.vamberto.School.mappers;

import com.vamberto.School.dtos.LoanDTO;
import com.vamberto.School.models.Loan;

public class LoanMapper {
    public static LoanDTO toDTO (Loan loan){
        return new LoanDTO(loan.getUserId(), loan.getBookId());
    }
}
