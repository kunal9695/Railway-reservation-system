package com.RailwayReservationAdminManagement.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
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

import com.RailwayReservationAdminManagement.model.TrainDetails;
import com.RailwayReservationAdminManagement.service.AdminServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {
	@Autowired
	private AdminServiceImpl adminServiceImpl;
    
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/all")
	@ApiOperation(value = "Get all train details")
	public ResponseEntity<List<TrainDetails>> getAllDetails() {
		LOGGER.info("get train started");
		List<TrainDetails> train = adminServiceImpl.getAllDetails();
		if(train.isEmpty()) {
			LOGGER.debug("Train not found");
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
    	}
		    LOGGER.debug("Trains are");
	        return ResponseEntity.of(Optional.of(train));			
	}
	
	
	@GetMapping("/search/{trainNo}")
	@ApiOperation(value = "Get all train details by Train Number")
	public ResponseEntity<TrainDetails> getDetailsByTrainNo(@Valid @PathVariable Integer trainNo) {   //@Valid added
		LOGGER.info("Get id Started");
		Optional<TrainDetails> train = Optional.of(adminServiceImpl.getDetailsByTrainNo(trainNo));
		  if (train.isPresent()) {
			  LOGGER.debug("Train is");
		    return new ResponseEntity<>(train.get(), HttpStatus.OK);
		  } else {
			  LOGGER.debug("Train not found"); 
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}

	@PostMapping("/addtrain")
	@ApiOperation(value = "Add new train details to train database")
	public ResponseEntity<TrainDetails> addTrainDetails(@Valid @RequestBody TrainDetails trainDetails) {
		LOGGER.info("Post train Started");
		TrainDetails t = null;
		try {
			t = adminServiceImpl.addTrainDetails(trainDetails);
			LOGGER.error("Train added");
			return new ResponseEntity<>(t, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{trainNo}")
	@ApiOperation(value = "Update train details in train database by Train Number")
	public ResponseEntity<String> updateTrainDetails(@PathVariable Integer trainNo,
			@Valid @RequestBody TrainDetails trainDetails) {
		LOGGER.info("Put Started");
		try {
			LOGGER.debug("Train updated");
		adminServiceImpl.updateTrainDetails(trainNo, trainDetails);
		return new ResponseEntity<>("Updated Train Details With Train No -" + trainDetails.getTrainNo() + " Successfully..!!",HttpStatus.OK);
		}catch(Exception e) {
			LOGGER.error("Train not found");
		  return new ResponseEntity<>("Train not found with id-" + trainNo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
	@DeleteMapping("/delete/{trainNo}")
	@ApiOperation(value = "Delete train details in train database by Train Number")
	public ResponseEntity<String> deleteTrainDetails(@PathVariable Integer trainNo) {
		LOGGER.info("Delete id Started");
		try {
			adminServiceImpl.deleteTrainDetails(trainNo);
		    return new ResponseEntity<>("Removed Train Details With Train No -" + trainNo, HttpStatus.ACCEPTED);
		  } catch (IllegalArgumentException e) {
			  LOGGER.error("Not found id");
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	/**Update The No Of Seats Based On The No Of Passengers That Booked Ticket**/
	@GetMapping("/updateSeats/{trainNo}/{noOfPassengers}")
	public void updateSeats(@PathVariable int trainNo,@PathVariable int noOfPassengers)
	{
		LOGGER.info("Update seats Started");
		TrainDetails currentTrain=adminServiceImpl.getDetailsByTrainNo(trainNo);
		int initialSeats=currentTrain.getNoOfSeats();
		int finalSeats=initialSeats-noOfPassengers;
		currentTrain.setNoOfSeats(finalSeats);
		adminServiceImpl.updateTrainDetails(trainNo, currentTrain);
		LOGGER.info("Seat updated");
	}

}
