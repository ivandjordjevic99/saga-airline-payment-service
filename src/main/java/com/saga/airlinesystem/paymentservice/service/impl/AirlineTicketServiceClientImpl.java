package com.saga.airlinesystem.paymentservice.service.impl;

import com.saga.airlinesystem.paymentservice.dto.TicketOrderUpdateRequest;
import com.saga.airlinesystem.paymentservice.dto.TicketOrderUpdateResponse;
import com.saga.airlinesystem.paymentservice.service.AirlineTicketServiceClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirlineTicketServiceClientImpl implements AirlineTicketServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<TicketOrderUpdateResponse> sendUpdateToAirlineTicketService(TicketOrderUpdateRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TicketOrderUpdateRequest> entity = new HttpEntity<>(request, headers);

        try {
            String endpoint = "http://localhost:8081/ticket-orders/process-payment";
            return restTemplate.exchange(
                    endpoint,
                    HttpMethod.PUT,
                    entity,
                    TicketOrderUpdateResponse.class
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
