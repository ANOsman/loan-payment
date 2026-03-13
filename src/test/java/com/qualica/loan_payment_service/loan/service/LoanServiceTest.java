package com.qualica.loan_payment_service.loan.service;

import com.qualica.loan_payment_service.loan.entity.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Test
    void shouldCreateLoanSuccessfully() {

        Loan loan = loanService.createLoan(new BigDecimal("1000"), 12);

        assertNotNull(loan.getLoanId());
        assertEquals(new BigDecimal("1000"), loan.getLoanAmount());
        assertEquals(new BigDecimal("1000"), loan.getBalance());
        assertEquals(Loan.LoanStatus.ACTIVE, loan.getStatus());
    }
}