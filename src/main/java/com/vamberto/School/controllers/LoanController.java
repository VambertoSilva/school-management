package com.vamberto.School.controllers;

import com.vamberto.School.Exception.LoanNotFoundException;
import com.vamberto.School.Exception.LoanRenewInvalidDate;
import com.vamberto.School.dtos.LoanDTO;
import com.vamberto.School.models.Loan;
import com.vamberto.School.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody LoanDTO DTO){

        try{
            Loan response = loanService.createLoan(DTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("Erro: ", e.getMessage()));
        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<Loan> returnLoan(@PathVariable String id){

        Loan reponse = loanService.returnLoan(id);

        return ResponseEntity.ok(reponse);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> listLoan(){

        List<Loan>  listLoan = loanService.listLoan();

        return ResponseEntity.ok(listLoan);

    }

    @PutMapping("/renew/{id}")
    public ResponseEntity<Void> renewingLoan(@PathVariable String id) {
        try {
            loanService.renewingLoan(id);

            return ResponseEntity.ok().build();
        }catch (LoanRenewInvalidDate | LoanNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
