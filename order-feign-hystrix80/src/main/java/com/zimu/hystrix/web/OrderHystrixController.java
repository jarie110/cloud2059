package com.zimu.hystrix.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zimu.hystrix.service.IFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
//@DefaultProperties(defaultFallback = "globlePayment_Fallback")
@Slf4j
public class OrderHystrixController {
    @Resource
    private IFeign iFeign;
    @GetMapping("timeout/{id}")
    @HystrixCommand(fallbackMethod="timeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
//    @HystrixCommand
    public String timeout(@PathVariable("id") Long id){
        log.info("hello&****************************************");
//        int i=10/0;
        return iFeign.timeout(id);
    }
//
    public String timeoutHandler(@PathVariable("id") Long id){
        return "我是消费者80，对方系统繁忙,请稍后再试";
    }

//
//    public String globlePayment_Fallback(){
//        return "全局异常处理信息";
//    }
    @GetMapping("circuit/{id}")
    @HystrixCommand(fallbackMethod="circuitHandler",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="60")

    })
    public String circuit(@PathVariable("id") int id){
        if(id<0){
            throw new RuntimeException();
        }
        return "id>0,ok~O(∩_∩)O哈哈~";
    }
    public String  circuitHandler(@PathVariable("id") int id){
        return "请稍后再试o(╥﹏╥)o";
    }
}
