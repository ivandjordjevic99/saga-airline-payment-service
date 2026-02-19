package com.saga.airlinesystem.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TicketOrderUpdateRequest {

    @JsonProperty("ticket_order_id")
    private final String reservationId;
}
