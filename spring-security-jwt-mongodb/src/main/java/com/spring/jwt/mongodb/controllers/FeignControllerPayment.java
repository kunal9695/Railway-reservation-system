package com.spring.jwt.mongodb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.mongodb.models.PaymentDetails;
import com.spring.jwt.mongodb.util.FeignClientPayment;

@RestController
@RequestMapping("/pay")
public class FeignControllerPayment {

	@Autowired
	private FeignClientPayment feignpayment;
	
	@GetMapping("/all")
	public List<PaymentDetails> getAll(@RequestHeader("Authorization") String token) {
		return feignpayment.getAll(token);
	}
	
	@PostMapping("/add")
	public String addPaymentDetails(@RequestHeader("Authorization") String token, @Valid @RequestBody PaymentDetails payment) {
		return feignpayment.addPaymentDetails(token, payment);
	}
	
}
