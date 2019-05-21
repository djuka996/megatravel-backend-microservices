package com.example.mikroservisStefan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MikroservisStefanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikroservisStefanApplication.class, args);
	}

}
