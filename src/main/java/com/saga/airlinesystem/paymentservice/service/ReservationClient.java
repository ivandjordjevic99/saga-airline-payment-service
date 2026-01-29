package com.saga.airlinesystem.paymentservice.service;

import com.saga.airlinesystem.paymentservice.dto.ReservationUpdateRequest;
import com.saga.airlinesystem.paymentservice.dto.ReservationUpdateResponse;
import org.springframework.http.ResponseEntity;

public interface ReservationClient {

    ResponseEntity<ReservationUpdateResponse> sendUpdateToReservationService(ReservationUpdateRequest request);
}
