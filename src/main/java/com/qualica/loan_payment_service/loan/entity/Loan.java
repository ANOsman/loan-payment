package com.qualica.loan_payment_service.loan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be greater than 0")
    private BigDecimal loanAmount;

    private BigDecimal balance;

    @NotNull(message = "Loan term is required")
    @Min(value = 1, message = "Loan term must be at least 1 month")
    private Integer term;

    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.ACTIVE;

    public enum LoanStatus {
        ACTIVE,
        SETTLED
    }
}