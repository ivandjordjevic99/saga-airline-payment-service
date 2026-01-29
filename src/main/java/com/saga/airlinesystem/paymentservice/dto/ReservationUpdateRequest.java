package com.saga.airlinesystem.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReservationUpdateRequest {

    @JsonProperty("reservation_id")
    private final String reservationId;
}
