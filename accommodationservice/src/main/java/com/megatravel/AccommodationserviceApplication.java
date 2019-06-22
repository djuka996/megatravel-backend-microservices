package com.megatravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.megatravel.webservices.AccommodationTypeServiceImpl;
import com.megatravel.webservices.AddressServiceImpl;
import com.megatravel.webservices.ExtraOptionServiceImpl;
import com.megatravel.webservices.HotelServiceImpl;
import com.megatravel.webservices.ReservationServiceImpl;
import com.megatravel.webservices.RoomServiceImpl;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@Import({AccommodationTypeServiceImpl.class,AddressServiceImpl.class,ExtraOptionServiceImpl.class,HotelServiceImpl.class,ReservationServiceImpl.class,RoomServiceImpl.class})
public class AccommodationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccommodationserviceApplication.class, args);
	}

}
