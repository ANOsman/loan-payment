package com.qualica.loan_payment_service.loan.repository;

import com.qualica.loan_payment_service.loan.entity.Loan;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}