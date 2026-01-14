package com.saga.airlinesystem.paymentservice.service;

import com.saga.airlinesystem.paymentservice.dto.PaymentRequestDto;
import com.saga.airlinesystem.paymentservice.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);
}
