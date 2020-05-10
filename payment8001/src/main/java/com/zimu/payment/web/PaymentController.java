package com.zimu.payment.web;

import com.mysql.jdbc.TimeUtil;
import com.zimu.entities.CommonResult;
import com.zimu.entities.Payment;
import com.zimu.payment.service.PaymentService;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private PaymentService paymentService;
    @PostMapping("create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        int res = paymentService.createPayment(payment);
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

    @GetMapping("discovery")
    public DiscoveryClient discovery(){
        //获取服务名称
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("服务名："+service);
        }
        //获取指定服务名称下的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info("主机名"+instance.getHost()+"\t"+"端口名："+instance.getPort()+"\t"+"uri"+instance.getUri());
        }
        return discoveryClient;
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
