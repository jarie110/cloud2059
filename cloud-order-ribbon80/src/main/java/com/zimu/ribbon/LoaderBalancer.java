package com.zimu.ribbon;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoaderBalancer {
    //获取当前提供服务者的实例
    ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstances);
}
