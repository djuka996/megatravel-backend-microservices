package com.megatravel.zuulsvr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.megatravel.zuulsvr.filters.ErrorFilter;
import com.megatravel.zuulsvr.filters.PostFilter;
import com.megatravel.zuulsvr.filters.PreFilter;
import com.megatravel.zuulsvr.filters.RouteFilter;


@SpringBootApplication
@EnableZuulProxy
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
    
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
    
}

