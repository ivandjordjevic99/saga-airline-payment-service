package com.saga.airlinesystem.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReservationUpdateResponse {

    @JsonProperty("reservation_id")
    private String reservationId;
}
