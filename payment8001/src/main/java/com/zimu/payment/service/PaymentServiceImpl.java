package com.zimu.payment.service;

import com.zimu.payment.dao.PaymentMapper;
import com.zimu.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;
    @Override
    public int createPayment(Payment payment) {
        return paymentMapper.createPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
