package com.spring.jwt.mongodb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.mongodb.models.UserDetails;
import com.spring.jwt.mongodb.util.FeignClientBooking;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class FeignControllerBooking {

	@Autowired
	private FeignClientBooking feignbook;
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDetails>> getAll(@RequestHeader("Authorization") String token) {
		return feignbook.getAll(token);
	}
	
	@GetMapping("/getDetailsByPnrNo/{pnrNo}")
	public ResponseEntity<UserDetails> getUserDetailsById(@RequestHeader("Authorization") String token, @PathVariable long pnrNo) {
		return feignbook.getUserDetailsById(token, pnrNo);
	}
	
	@PostMapping("/book")
	public ResponseEntity<UserDetails> addUserDetails(@RequestHeader("Authorization") String token,@Valid @RequestBody UserDetails userDetails) {
		return feignbook.addUserDetails(token, userDetails);
	}
	
	@DeleteMapping("/cancel/{pnrNo}")
	public ResponseEntity<HttpStatus> deleteUserDetailsById(@RequestHeader("Authorization") String token, @PathVariable long pnrNo) {
		return feignbook.deleteUserDetailsById(token, pnrNo);
	}
	
}
