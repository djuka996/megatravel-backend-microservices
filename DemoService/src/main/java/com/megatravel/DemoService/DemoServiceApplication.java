package com.megatravel.DemoService;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.megatravel.DemoService.soap.services.ExampleWebServiceImpl;

@SpringBootApplication
@EnableEurekaClient
public class DemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceApplication.class, args);
		Endpoint.publish("http://localhost:8080/exampleWebServiceURL", new ExampleWebServiceImpl());
	}
	
	/*@PostConstruct
	public void publishEndpoints() {
		Endpoint.publish("http://localhost:8080/exampleWebServiceURL", new ExampleWebServiceImpl());
	}*/

}
