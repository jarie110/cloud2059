package com.zimu.payment.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Value("${server.port}")
    private String port;
    public String ok(Long id){
        return "*****port:"+port+",***id:"+id+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod="timeouthandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
//    @HystrixCommand
    public String timeout(Long id){
//        int  i = 10/0;
        int ms = 3000;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "*****port:"+port+",***id:"+id+"O(∩_∩)O哈哈~"+"耗时："+ms;
//        int i = 10/0;
//        return "*****port:"+port+",***id:"+id+"O(∩_∩)O哈哈~";
    }

    public String timeouthandler(Long id){
        return "*****port:"+port+",***id:"+id+"o(╥﹏╥)o";
    }


}
