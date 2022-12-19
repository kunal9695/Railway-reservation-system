package com.spring.jwt.mongodb.util;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.spring.jwt.mongodb.models.PaymentDetails;



@FeignClient(value="Payment-Service",url="http://localhost:8085/pay")
public interface FeignClientPayment {

	@GetMapping("/all")
	public List<PaymentDetails> getAll(@RequestHeader("Authorization") String token);
	
	@PostMapping("/add")
	public String addPaymentDetails(@RequestHeader("Authorization") String token, PaymentDetails payment);
	
}
