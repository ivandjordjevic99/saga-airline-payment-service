package com.saga.airlinesystem.paymentservice.service.impl;

import com.saga.airlinesystem.paymentservice.dto.PaymentRequestDto;
import com.saga.airlinesystem.paymentservice.dto.PaymentResponseDto;
import com.saga.airlinesystem.paymentservice.dto.ReservationUpdateRequest;
import com.saga.airlinesystem.paymentservice.dto.ReservationUpdateResponse;
import com.saga.airlinesystem.paymentservice.exceptions.customexceptions.PaymentFailedException;
import com.saga.airlinesystem.paymentservice.model.Payment;
import com.saga.airlinesystem.paymentservice.model.PaymentResolution;
import com.saga.airlinesystem.paymentservice.repository.PaymentRepository;
import com.saga.airlinesystem.paymentservice.service.PaymentService;
import com.saga.airlinesystem.paymentservice.service.ReservationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationClient reservationClient;

    @Override
    @Transactional
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        boolean successful = isPaymentSuccessful();
        Payment payment = new Payment();
        payment.setReservationId(paymentRequestDto.getReservationId());
        if (successful) {
            ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest(
                    paymentRequestDto.getReservationId().toString());
            ResponseEntity<ReservationUpdateResponse> response = reservationClient.sendUpdateToReservationService(
                    reservationUpdateRequest);
            if(response != null && response.getStatusCode().is2xxSuccessful()) {
                payment.setPaymentResolution(PaymentResolution.PAYMENT_SUCCESSFUL);
            } else {
                payment.setPaymentResolution(PaymentResolution.PAYMENT_FAILED_RESERVATION_SERVICE);
                throw new PaymentFailedException("Payment failed on reservation service");
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
        paymentResponseDto.setReservationId(payment.getReservationId());
        paymentResponseDto.setPaymentResolution(payment.getPaymentResolution());
        paymentResponseDto.setId(payment.getId());
        return paymentResponseDto;
    }
}
