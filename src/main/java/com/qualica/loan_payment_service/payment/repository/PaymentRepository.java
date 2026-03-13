package com.qualica.loan_payment_service.payment.repository;

import com.qualica.loan_payment_service.payment.entiry.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}