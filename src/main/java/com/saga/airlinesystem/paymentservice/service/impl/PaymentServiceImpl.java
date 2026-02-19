package com.saga.airlinesystem.paymentservice.service.impl;

import com.saga.airlinesystem.paymentservice.dto.PaymentRequestDto;
import com.saga.airlinesystem.paymentservice.dto.PaymentResponseDto;
import com.saga.airlinesystem.paymentservice.dto.TicketOrderUpdateRequest;
import com.saga.airlinesystem.paymentservice.dto.TicketOrderUpdateResponse;
import com.saga.airlinesystem.paymentservice.exceptions.customexceptions.PaymentFailedException;
import com.saga.airlinesystem.paymentservice.model.Payment;
import com.saga.airlinesystem.paymentservice.model.PaymentResolution;
import com.saga.airlinesystem.paymentservice.repository.PaymentRepository;
import com.saga.airlinesystem.paymentservice.service.PaymentService;
import com.saga.airlinesystem.paymentservice.service.AirlineTicketServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AirlineTicketServiceClient airlineTicketServiceClient;

    @Override
    @Transactional
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        boolean successful = isPaymentSuccessful();
        Payment payment = new Payment();
        payment.setTicketOrderId(paymentRequestDto.getTicketOrderId());
        if (successful) {
            TicketOrderUpdateRequest ticketOrderUpdateRequest = new TicketOrderUpdateRequest(
                    paymentRequestDto.getTicketOrderId().toString());
            ResponseEntity<TicketOrderUpdateResponse> response = airlineTicketServiceClient.sendUpdateToAirlineTicketService(
                    ticketOrderUpdateRequest);
            if(response != null && response.getStatusCode().is2xxSuccessful()) {
                payment.setPaymentResolution(PaymentResolution.PAYMENT_SUCCESSFUL);
            } else {
                payment.setPaymentResolution(PaymentResolution.PAYMENT_FAILED_TICKET_SERVICE);
            }
        } else {
            payment.setPaymentResolution(PaymentResolution.PAYMENT_FAILED_PAYMENT_SERVICE);
            throw new PaymentFailedException("Payment failed on payment service. No funds.");
        }

        paymentRepository.save(payment);
        return toDto(payment);
    }

    private boolean isPaymentSuccessful() {
        return ThreadLocalRandom.current().nextDouble() < 0.8;
    }

    private PaymentResponseDto toDto(Payment payment) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setTicketOrderId(payment.getTicketOrderId());
        paymentResponseDto.setPaymentResolution(payment.getPaymentResolution());
        return paymentResponseDto;
    }
}
