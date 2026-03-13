package com.qualica.loan_payment_service.loan.service;

import com.qualica.loan_payment_service.loan.entity.Loan;
import com.qualica.loan_payment_service.common.exception.LoanNotFoundException;
import com.qualica.loan_payment_service.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final LoanRepository loanRepository;

    public Loan createLoan(BigDecimal amount, Integer term) {

        log.info("Creating loan with amount {} and term {}", amount, term);

        Loan loan = new Loan();
        loan.setLoanAmount(amount);
        loan.setBalance(amount);
        loan.setTerm(term);
        loan.setStatus(Loan.LoanStatus.ACTIVE);

        Loan savedLoan = loanRepository.save(loan);

        log.info("Loan created successfully with ID {}", savedLoan.getLoanId());

        return savedLoan;
    }

    public Loan getLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + loanId));
    }
}