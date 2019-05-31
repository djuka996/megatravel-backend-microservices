package com.megatravel.xmlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class XmlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlServiceApplication.class, args);
	}

}
