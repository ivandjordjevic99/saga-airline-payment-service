package com.saga.airlinesystem.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentRequestDto {

    @JsonProperty("reservation_id")
    private UUID reservationId;
}
