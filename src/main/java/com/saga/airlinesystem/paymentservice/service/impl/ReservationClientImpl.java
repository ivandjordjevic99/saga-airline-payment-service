package com.saga.airlinesystem.paymentservice.service.impl;

import com.saga.airlinesystem.paymentservice.dto.ReservationUpdateRequest;
import com.saga.airlinesystem.paymentservice.dto.ReservationUpdateResponse;
import com.saga.airlinesystem.paymentservice.service.ReservationClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationClientImpl implements ReservationClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<ReservationUpdateResponse> sendUpdateToReservationService(ReservationUpdateRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReservationUpdateRequest> entity = new HttpEntity<>(request, headers);

        try {
            String endpoint = "http://localhost:8081/reservations/process-payment";
            return restTemplate.exchange(
                    endpoint,
                    HttpMethod.PUT,
                    entity,
                    ReservationUpdateResponse.class
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
