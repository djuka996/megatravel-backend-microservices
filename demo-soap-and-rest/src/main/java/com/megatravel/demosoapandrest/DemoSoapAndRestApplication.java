package com.megatravel.demosoapandrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@ComponentScan(basePackages = {"com.megatravel.demosoapandrest"})
public class DemoSoapAndRestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSoapAndRestApplication.class, args);
	}

}
