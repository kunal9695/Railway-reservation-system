package com.spring.jwt.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.*")
@SpringBootApplication
public class SpringSecurityJwtMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtMongodbApplication.class, args);
	}

}
