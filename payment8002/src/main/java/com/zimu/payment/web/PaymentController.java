package com.zimu.payment.web;

import com.zimu.payment.service.PaymentService;
import com.zimu.entities.CommonResult;
import com.zimu.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private PaymentService paymentService;
    @PostMapping("create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        int res = paymentService.   createPayment(payment);
        if(res>0){
            return new CommonResult(200,"创建成功,端口号："+port,res);
        }
        return new CommonResult(400,"创建失败",null);
    }

    @GetMapping("get/{id}")
            public CommonResult getPaymentById(@PathVariable("id") Long id) {
                Payment payment = paymentService.getPaymentById(id);
                if(payment != null){
                    log.info("haha");
            return new CommonResult(200,"查询成功，端口号："+port,payment);

        }
            return new CommonResult(444,"查询失败，您的查询id为"+id,null);
    }
    @GetMapping("lb")
    public String getPaymentLB(){
        return port;
    }

    @GetMapping("feign/timeout")
    public String paymentFeignTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
