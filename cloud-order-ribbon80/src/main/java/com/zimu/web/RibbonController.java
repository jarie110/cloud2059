package com.zimu.web;

import com.netflix.discovery.converters.Auto;
import com.zimu.ribbon.LoaderBalancer;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class RibbonController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoaderBalancer loaderBalancer;

    @GetMapping("consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loaderBalancer.getServiceInstance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
}
