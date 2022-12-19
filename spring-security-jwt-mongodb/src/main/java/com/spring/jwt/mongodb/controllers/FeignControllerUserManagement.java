package com.spring.jwt.mongodb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.mongodb.models.TrainDetails;
import com.spring.jwt.mongodb.models.UserSignUp;
import com.spring.jwt.mongodb.util.FeignClientUserManagement;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class FeignControllerUserManagement {

	@Autowired
	private FeignClientUserManagement feignuser;
	
	@PostMapping("/signup")
	public ResponseEntity<UserSignUp> saveUser(@Valid @RequestHeader("Authorization") String token, @RequestBody UserSignUp userSignUp) {
		return feignuser.saveUser(token, userSignUp);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<UserSignUp>> getAll(@RequestHeader("Authorization") String token) {
		return feignuser.getAll(token);
	}
	
//	@PutMapping("/update/{id}")
//	public ResponseEntity<String> updateUserDetails(@RequestHeader("Authorization") String token, @PathVariable Integer id,
//			@Valid @RequestBody UserSignUp userSignUp) {
//		return feignuser.updateUserDetails(token, id, userSignUp);
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserDetails(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
		return feignuser.deleteUserDetails(token, id);
	}
	
	@GetMapping("/alltrains")
	public ResponseEntity<List<TrainDetails>> getAllDetails(@RequestHeader("Authorization") String token) {
		return feignuser.getAllDetails(token);
	}
	
	@GetMapping("/status")
	public ResponseEntity<String> getStatus(@RequestHeader("Authorization") String token, @RequestParam long pnrNo) {
		return feignuser.getStatus(token, pnrNo);
	}
	
//	@GetMapping("/trainNo")
//	public ResponseEntity<TrainDetails> getDetailsByTrainNo(@RequestHeader("Authorization") String token, @Valid @PathVariable Integer trainNo){
//		return feignuser.getDetailsByTrainNo(token, trainNo);
//	}
	
	@GetMapping("/trainNo")
	public ResponseEntity<TrainDetails> getDetailsByTrainNo(@RequestHeader("Authorization") String token, @PathVariable Integer trainNo) {
		return feignuser.getDetailsByTrainNo(token, trainNo);
	}
	
//	@GetMapping("/route/{sourceStation}/{destinationStation}")
//	public ResponseEntity<List<TrainDetails>> getTrainDetailsByStartStation(@RequestHeader("Authorization") String token, @PathVariable String sourceStation,
//			@PathVariable String destinationStation){
//		return feignuser.getTrainDetailsByStartStation(token, sourceStation, destinationStation);
//	}
	
}
