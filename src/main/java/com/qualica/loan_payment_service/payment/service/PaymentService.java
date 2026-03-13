package com.qualica.loan_payment_service.payment.service;

import com.qualica.loan_payment_service.common.exception.LoanNotFoundException;
import com.qualica.loan_payment_service.common.exception.OverpaymentException;
import com.qualica.loan_payment_service.loan.entity.Loan;
import com.qualica.loan_payment_service.loan.repository.LoanRepository;
import com.qualica.loan_payment_service.payment.entiry.Payment;
import com.qualica.loan_payment_service.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LoanRepository loanRepository;

    @Transactional
    public Payment makePayment(Long loanId, BigDecimal amount) {

        log.info("Processing payment of {} for loan {}", amount, loanId);

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + loanId));

        // Check if the loan is already settled
        if (loan.getStatus() == Loan.LoanStatus.SETTLED) {
            throw new OverpaymentException("Loan is already settled for id");
        }

        // Check overpayment
        if (amount.compareTo(loan.getBalance()) > 0) {
            throw new OverpaymentException("Payment exceeds remaining balance: " + loan.getBalance());
        }

        // Update remaining balance
        BigDecimal newBalance = loan.getBalance().subtract(amount);
        loan.setBalance(newBalance);

        // Update status if fully paid
        if (newBalance.compareTo(BigDecimal.ZERO) == 0) {
            loan.setStatus(Loan.LoanStatus.SETTLED);
        }

        loanRepository.save(loan);

        Payment payment = new Payment();
        payment.setLoanId(loanId);
        payment.setPaymentAmount(amount);

        Payment savedPayment = paymentRepository.save(payment);

        log.info("Payment {} recorded for loan {}", savedPayment.getPaymentId(), loanId);

        return savedPayment;
    }
}