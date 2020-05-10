package com.zimu.web;

import com.zimu.entities.CommonResult;
import com.zimu.feign.IFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
public class FeignController {
    @Autowired
    private IFeign iFeign;
    @GetMapping("get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return iFeign.getPaymentById(id);
    }
    @GetMapping("feign/timeout")
    public String paymentFeignTimeout(){
        return iFeign.paymentFeignTimeout();
    }
}
