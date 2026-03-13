package com.qualica.loan_payment_service.payment.controller;

import com.qualica.loan_payment_service.payment.entiry.Payment;
import com.qualica.loan_payment_service.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment makePayment(@Valid @RequestBody Payment payment) {

        return paymentService.makePayment(
                payment.getLoanId(),
                payment.getPaymentAmount()
        );
    }
}