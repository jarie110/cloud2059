package com.zimu.hystrix.service;

import org.springframework.stereotype.Component;

@Component
public class FeignImplFallBack implements IFeign {
    @Override
    public String timeout(Long id) {
        return "feign的实现类降级处理";
    }
}
