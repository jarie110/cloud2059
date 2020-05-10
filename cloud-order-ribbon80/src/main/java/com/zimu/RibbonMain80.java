package com.zimu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class RibbonMain80 {
    public static void main(String[] args) {
        SpringApplication.run(RibbonMain80.class, args);
    }

    @Bean("restTemplate")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
