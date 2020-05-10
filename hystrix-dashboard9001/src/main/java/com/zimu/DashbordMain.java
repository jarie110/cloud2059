package com.zimu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashbordMain {
    public static void main(String[] args) {
        SpringApplication.run(DashbordMain.class, args);
    }
}
