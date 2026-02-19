package com.saga.airlinesystem.paymentservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "ticket_order_id", nullable = false)
    private UUID ticketOrderId;

    @Column(name = "payment_resolution", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentResolution paymentResolution;
}
