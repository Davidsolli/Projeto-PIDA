package com.david.amazonas.repositories;

import com.david.amazonas.domains.payments.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
