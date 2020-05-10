package com.zimu.hystrix.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PAYMENT-FEIGN-HYSTRIX"/*,fallback = FeignImplFallBack.class*/)
public interface IFeign {
    @GetMapping("payment/timeout/{id}")
    public String timeout(@PathVariable("id") Long id);
}
