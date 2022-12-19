package com.RailwayReservationPaymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
/**
 * Main Method For Payment Microservice
 */
@SpringBootApplication
//@EnableEmailTools
@EnableEurekaClient
public class RailwayReservationPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayReservationPaymentServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	} 
}
