package com.saga.airlinesystem.paymentservice.exceptions.customexceptions;

public class PaymentFailedException extends AbstractException {

    public PaymentFailedException(String message) {
        super(403, message);
    }

    public PaymentFailedException() {
        super(403, "Payment failed");
    }
}
