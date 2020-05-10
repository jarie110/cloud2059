package com.zimu.payment.dao;

import com.zimu.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    int createPayment(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
