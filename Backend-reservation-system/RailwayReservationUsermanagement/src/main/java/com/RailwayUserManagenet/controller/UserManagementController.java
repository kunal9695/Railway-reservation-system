package com.RailwayUserManagenet.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RailwayUserManagenet.model.TrainDetails;
import com.RailwayUserManagenet.model.UserSignUp;
import com.RailwayUserManagenet.service.SignUpService;
import com.RailwayUserManagenet.service.UserServiceImpl;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin("http://localhost:3000")

@RequestMapping("/users")
public class UserManagementController {
	/* Autowired SignUpService Object */
	@Autowired
	private SignUpService signUpService;

	/* Autowired UserServiceImpl Object */
	@Autowired
	private UserServiceImpl userServiceImpl;

	/* This Method Is Used To Add User Details */
	@PostMapping("/signup")
	@ApiOperation(value = "To Add User Details")
	public ResponseEntity<UserSignUp> saveUser(@Valid @RequestBody UserSignUp userSignUp) {
		UserSignUp t = null;
		try {
			t = signUpService.addUser(userSignUp);
			return new ResponseEntity<>(t, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/* This Method Is Used To Display All Users */
	@GetMapping("/allusers")
	@ApiOperation(value = "To Get All The User Details")
	public ResponseEntity<List<UserSignUp>> getAll() {
		List<UserSignUp> userSignUp = signUpService.getuser();
		if(userSignUp.isEmpty()) {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userSignUp, HttpStatus.OK);
	}
	
	/* This Method Is Used To Update Users By Id */
	@PutMapping("/update/{id}")
	@ApiOperation(value = "To Update User Details")
	public ResponseEntity<String> updateUserDetails(@PathVariable Integer id,
			@Valid @RequestBody UserSignUp userSignUp) {
		try {
			signUpService.updateUser(id, userSignUp);
		return new ResponseEntity<>("Updated User Details With Id -" + userSignUp.getId() + " Successfully..!!",HttpStatus.OK);
		}catch(Exception e) {
		  return new ResponseEntity<>("User not found with id-" + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/* This Method Is Used To Delete Users By Id */
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "To Delete User Details")
	public ResponseEntity<String> deleteUserDetails(@PathVariable Integer id) {
		try {
			signUpService.deleteUser(id);
		    return new ResponseEntity<>("Removed User Details With id -" + id, HttpStatus.ACCEPTED);
		  } catch (IllegalArgumentException e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	

	@GetMapping("/alltrains")
	@ApiOperation(value = "Get all train details")
	public ResponseEntity<List<TrainDetails>> getAllDetails() {
		List<TrainDetails> train = userServiceImpl.getAllDetails();
		if(train.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
    	}
	        return ResponseEntity.of(Optional.of(train));			
	}
	

	/* This Method Is Used To See The Ststus Of Train Using PNRNO */
	@GetMapping("/status")
	@ApiOperation(value = "Get status of your booking by Pnr Number")
	public ResponseEntity<String> getStatus(@RequestParam long pnrNo) {
		try {
			userServiceImpl.pnrStatus(pnrNo);
		    return new ResponseEntity<>("The Status Of The Booking  With Pnr No -" + pnrNo, HttpStatus.ACCEPTED);
		}
		    catch (IllegalArgumentException e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }

		
		
	}

	/* This Method Is Used To Display Train Details Using TrainNo check*/
	@GetMapping("/trainNo")
	@ApiOperation(value = "Get train details by Train Number")
	public ResponseEntity<TrainDetails> getDetailsByTrainNo(@PathVariable Integer trainNo) {
		Optional<TrainDetails> train = Optional.of(userServiceImpl.getDetailsByTrainNo(trainNo));
		  if (train.isPresent()) {
		    return new ResponseEntity<>(train.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}
	/**
	 * This Method Is Used To Display Train Details Using Source And Destination
	 **/
	@GetMapping("/route/{sourceStation}/{destinationStation}")
	@ApiOperation(value = "Get train details by giving start and final locations")
	public ResponseEntity<List<TrainDetails>> getTrainDetailsByStartStation(@PathVariable String sourceStation,
			@PathVariable String destinationStation) {
	List<TrainDetails> train = userServiceImpl.getTrainDetailsByEndPoints(sourceStation,destinationStation);
	if(train.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
        return ResponseEntity.of(Optional.of(train));			
}
		//return userServiceImpl.getTrainDetailsByEndPoints(sourceStation, destinationStation);
}