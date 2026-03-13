package com.qualica.loan_payment_service.payment.service;

import com.qualica.loan_payment_service.common.exception.OverpaymentException;
import com.qualica.loan_payment_service.loan.entity.Loan;
import com.qualica.loan_payment_service.loan.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private PaymentService paymentService;

    @Test
    void paymentShouldReduceLoanBalance() {

        Loan loan = loanService.createLoan(new BigDecimal("1000"), 12);

        paymentService.makePayment(loan.getLoanId(), new BigDecimal("200"));

        Loan updatedLoan = loanService.getLoan(loan.getLoanId());

        assertEquals(0, updatedLoan.getBalance().compareTo(new BigDecimal("800")));
    }

    @Test
    void shouldThrowExceptionForOverpayment() {

        Loan loan = loanService.createLoan(new BigDecimal("500"), 12);

        assertThrows(OverpaymentException.class, () ->
                paymentService.makePayment(loan.getLoanId(), new BigDecimal("600"))
        );
    }

    @Test
    void loanShouldBeSettledWhenFullyPaid() {

        Loan loan = loanService.createLoan(new BigDecimal("1000"), 12);

        paymentService.makePayment(loan.getLoanId(), new BigDecimal("1000"));

        Loan updatedLoan = loanService.getLoan(loan.getLoanId());

        assertTrue(updatedLoan.getBalance().compareTo(BigDecimal.ZERO) == 0);
        assertEquals(Loan.LoanStatus.SETTLED, updatedLoan.getStatus());
    }
}