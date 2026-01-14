package com.saga.airlinesystem.paymentservice.repository;

import com.saga.airlinesystem.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
