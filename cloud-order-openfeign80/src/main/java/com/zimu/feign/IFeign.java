package com.zimu.feign;

import com.zimu.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface IFeign {
    @GetMapping("payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);
    @GetMapping("payment/feign/timeout")
    String paymentFeignTimeout();
}
