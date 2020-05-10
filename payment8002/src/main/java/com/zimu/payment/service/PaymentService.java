package com.zimu.payment.service;


import com.zimu.entities.Payment;

public interface PaymentService {
    public int createPayment(Payment payment);
    public Payment getPaymentById(Long id);
}
