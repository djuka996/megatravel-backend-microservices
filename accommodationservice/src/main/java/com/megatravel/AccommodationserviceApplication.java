package com.megatravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.megatravel.aspect.AspectLogging;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableAspectJAutoProxy
@Import({AspectLogging.class})
public class AccommodationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccommodationserviceApplication.class, args);
	}

}
