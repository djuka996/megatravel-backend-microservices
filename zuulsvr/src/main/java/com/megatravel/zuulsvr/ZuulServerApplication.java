package com.megatravel.zuulsvr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.megatravel.zuulsvr.filters.PostFilter;
import com.megatravel.zuulsvr.filters.PreFilter;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
    
    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

}

