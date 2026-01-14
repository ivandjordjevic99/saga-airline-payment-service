package com.saga.airlinesystem.paymentservice.controller;

import com.saga.airlinesystem.paymentservice.dto.PaymentRequestDto;
import com.saga.airlinesystem.paymentservice.dto.PaymentResponseDto;
import com.saga.airlinesystem.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(paymentRequestDto));
    }

}
