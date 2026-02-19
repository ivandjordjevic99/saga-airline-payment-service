package com.saga.airlinesystem.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentRequestDto {

    @JsonProperty("ticket_order_id")
    private UUID ticketOrderId;
}
