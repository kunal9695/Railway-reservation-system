package com.spring.jwt.mongodb.util;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.spring.jwt.mongodb.models.UserDetails;

@FeignClient(value="Booking-Service",url="http://localhost:8084/user")
public interface FeignClientBooking {

	@GetMapping("/all") 
	public ResponseEntity<List<UserDetails>> getAll(@RequestHeader("Authorization") String token);
	
	@GetMapping("/getDetailsByPnrNo/{pnrNo}")
	public ResponseEntity<UserDetails> getUserDetailsById(@RequestHeader("Authorization") String token,long pnrNo);
	
	@PostMapping("/book")
	public ResponseEntity<UserDetails> addUserDetails(@RequestHeader("Authorization") String token, UserDetails userDetails);
	
	@DeleteMapping("/cancel/{pnrNo}")
	public ResponseEntity<HttpStatus> deleteUserDetailsById(@RequestHeader("Authorization") String token, long pnrNo);

}
