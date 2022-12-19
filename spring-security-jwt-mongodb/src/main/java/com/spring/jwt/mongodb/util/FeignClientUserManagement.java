package com.spring.jwt.mongodb.util;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.jwt.mongodb.models.TrainDetails;
import com.spring.jwt.mongodb.models.UserSignUp;



@FeignClient(value="UserManagement-Service",url="http://localhost:8082/users")
public interface FeignClientUserManagement {

	@PostMapping("/signup")
	public ResponseEntity<UserSignUp> saveUser(@RequestHeader("Authorization") String token, UserSignUp userSignUp);
	
	@GetMapping("/allusers")
	public ResponseEntity<List<UserSignUp>> getAll(@RequestHeader("Authorization") String token);
	
//	@PutMapping("/update/{id}")
//	public ResponseEntity<String> updateUserDetails(@RequestHeader("Authorization") String token, Integer id, UserSignUp userSignUp);
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserDetails(@RequestHeader("Authorization") String token, Integer id);
	
	@GetMapping("/alltrains")
	public ResponseEntity<List<TrainDetails>> getAllDetails(@RequestHeader("Authorization") String token);
	
	@GetMapping("/status")
	public ResponseEntity<String> getStatus(@RequestHeader("Authorization") String token, long pnrNo);
	
	@GetMapping("/trainNo")
	public ResponseEntity<TrainDetails> getDetailsByTrainNo(@RequestHeader("Authorization") String token, Integer trainNo);
	
//	@GetMapping("/route/{sourceStation}/{destinationStation}")
//	public ResponseEntity<List<TrainDetails>> getTrainDetailsByStartStation(@RequestHeader("Authorization") String token, String sourceStation, String destinationStation);
	
}
