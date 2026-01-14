package com.saga.airlinesystem.paymentservice.service.impl;

import com.saga.airlinesystem.paymentservice.dto.PaymentRequestDto;
import com.saga.airlinesystem.paymentservice.dto.PaymentResponseDto;
import com.saga.airlinesystem.paymentservice.model.Payment;
import com.saga.airlinesystem.paymentservice.model.PaymentResolution;
import com.saga.airlinesystem.paymentservice.repository.PaymentRepository;
import com.saga.airlinesystem.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        boolean successful = isPaymentSuccessful();
        Payment payment = new Payment();
        payment.setReservationId(paymentRequestDto.getReservationId());
        payment.setPaymentResolution(
                successful ? PaymentResolution.PAYMENT_SUCCESSFUL : PaymentResolution.PAYMENT_FAILED
        );

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
