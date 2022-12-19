package com.spring.jwt.mongodb.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.mongodb.models.TrainDetails;
import com.spring.jwt.mongodb.util.FeignClientAdmin;



@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class FeignControllerAdmin {
	
	@Autowired
	private FeignClientAdmin feignadmin;
	
	@GetMapping("/all") 
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<List<TrainDetails>> getAllDetails(@RequestHeader("Authorization") String token){	
		return feignadmin.getAllDetails(token);
	}
	
	@GetMapping("/search/{trainNo}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<TrainDetails> getDetailsByTrainNo(@RequestHeader("Authorization") String token, @Valid@RequestBody @PathVariable Integer trainNo) {
		return feignadmin.getDetailsByTrainNo(token,trainNo);
	} 
	
	@PostMapping("/addtrain")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<TrainDetails> addTrainDetails( @RequestHeader("Authorization") String token,@Valid @RequestBody TrainDetails trainDetails) {
		 return feignadmin.addTrainDetails(token, trainDetails);
	}
	
	@PutMapping("/update/{trainNo}")
	@PreAuthorize(" hasRole('ADMIN')")
	public String updateTrainDetail(@RequestHeader("Authorization")String token, @PathVariable Integer id,
			@Valid @RequestBody TrainDetails trainDetails) {
		 return feignadmin.updateTrainDetail(token, trainDetails, id);
	}
	
	@DeleteMapping("/delete/{trainNo}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<String> deleteTrainDetails(@RequestHeader("Authorization")String token, @PathVariable Integer trainNo) {
		return feignadmin.deleteTrainDetails(token, trainNo);
	}
	
}
