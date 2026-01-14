package com.saga.airlinesystem.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saga.airlinesystem.paymentservice.model.PaymentResolution;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDto {

    private UUID id;

    @JsonProperty("reservation_id")
    private UUID reservationId;

    @JsonProperty("payment_resolution")
    private PaymentResolution paymentResolution;
}
