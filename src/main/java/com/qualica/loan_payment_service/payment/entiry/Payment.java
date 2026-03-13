package com.qualica.loan_payment_service.payment.entiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @NotNull(message = "Payment amount is required")
    @Positive(message = "Payment amount must be greater than 0")
    private BigDecimal paymentAmount;
}