package com.saga.airlinesystem.paymentservice.service;

import com.saga.airlinesystem.paymentservice.dto.TicketOrderUpdateRequest;
import com.saga.airlinesystem.paymentservice.dto.TicketOrderUpdateResponse;
import org.springframework.http.ResponseEntity;

public interface AirlineTicketServiceClient {

    ResponseEntity<TicketOrderUpdateResponse> sendUpdateToAirlineTicketService(TicketOrderUpdateRequest request);
}
