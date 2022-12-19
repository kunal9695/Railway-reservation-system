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
import org.springframework.web.bind.annotation.RequestHeader;

import com.spring.jwt.mongodb.models.TrainDetails;

@FeignClient(value="Admin-Service",url="http://localhost:8083/admin")
public interface FeignClientAdmin {

	@GetMapping("/all") 
	public ResponseEntity<List<TrainDetails>> getAllDetails(@RequestHeader("Authorization") String token);
	
	@GetMapping("/search/{trainNo}")
	public ResponseEntity<TrainDetails> getDetailsByTrainNo(@RequestHeader("Authorization") String token,@PathVariable Integer trainNo);
	
	@PostMapping("/addtrain") 
	public ResponseEntity<TrainDetails> addTrainDetails(@RequestHeader("Authorization") String token,TrainDetails train);
	
	@PutMapping("/update/{trainNo}")
    public String updateTrainDetail(@RequestHeader("Authorization") String token,TrainDetails train, @PathVariable Integer id);
	
	@DeleteMapping("/delete/{trainNo}")
	public ResponseEntity<String> deleteTrainDetails( @RequestHeader("Authorization") String token,@PathVariable Integer trainNo);
	
//	@GetMapping("/updateSeats/{trainNo}/{noOfPassengers}")
//	public void updateSeats(@RequestHeader("Authorization") String token, int trainNo, int noOfPassengers);


	
}
