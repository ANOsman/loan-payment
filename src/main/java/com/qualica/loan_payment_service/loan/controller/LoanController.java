package com.qualica.loan_payment_service.loan.controller;

import com.qualica.loan_payment_service.loan.entity.Loan;
import com.qualica.loan_payment_service.loan.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Loan createLoan(@Valid @RequestBody Loan loan) {
        return loanService.createLoan(
                loan.getLoanAmount(),
                loan.getTerm()
        );
    }

    @GetMapping("/{loanId}")
    public Loan getLoan(@PathVariable Long loanId) {
        return loanService.getLoan(loanId);
    }

}