package com.qualica.loan_payment_service.common.exception;

public class OverpaymentException extends RuntimeException {

    public OverpaymentException(String message) {
        super(message);
    }
}