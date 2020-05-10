package com.zimu.payment.web;

import com.zimu.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("payment")
public class PaymentHystrixController {
    @Resource
    private PaymentService paymentService;
    @GetMapping("ok/{id}")
    public String ok(@PathVariable("id") Long id){
        return paymentService.ok(id);
    }

    @GetMapping("timeout/{id}")
    public String timeout(@PathVariable("id") Long id){
       return paymentService.timeout(id);
    }
}
