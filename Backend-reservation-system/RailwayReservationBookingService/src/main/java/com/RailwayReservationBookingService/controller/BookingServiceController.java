package com.RailwayReservationBookingService.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import com.RailwayReservationBookingService.model.TrainDetails;
import com.RailwayReservationBookingService.model.UserDetails;
import com.RailwayReservationBookingService.service.BookingServiceImpl;

import io.swagger.annotations.ApiOperation;

@Component
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class BookingServiceController {
	@Autowired
	private BookingServiceImpl bookingServiceImpl;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/all")
	@ApiOperation(value = "Get all user details who booked their tickets")
	public ResponseEntity<List<UserDetails>> getAll() {
		List<UserDetails> userdetails = bookingServiceImpl.getAll();
		if(userdetails.isEmpty()) {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userdetails, HttpStatus.OK);
	}

	@GetMapping("/getDetailsByPnrNo/{pnrNo}")
	@ApiOperation(value = "Get user details by Pnr Number")
	public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable long pnrNo) {
		return new ResponseEntity<>(bookingServiceImpl.getUserDetailsById(pnrNo), HttpStatus.OK);
	}

	@PostMapping("/book")
	@ApiOperation(value = "Book a ticket")
	public ResponseEntity<UserDetails> addUserDetails(@Valid @RequestBody UserDetails userDetails) {
		try{
		userDetails.setId(bookingServiceImpl.getSequenceNumber(UserDetails.SEQUENCE_NAME));
		userDetails.setPnrNo();
		userDetails.setPayment("Pending");
		UserDetails user = bookingServiceImpl.addUserBookingDetails(userDetails);
		int trainNo = userDetails.getTrainNo();
		int noOfAdults = userDetails.getAdults();
		int noOfChildren = userDetails.getChildren();
		int totalPassengers = noOfAdults + noOfChildren;
		restTemplate.getForObject("http://localhost:8083/admin/updateSeats/" + trainNo + "/" + totalPassengers,
				TrainDetails.class);
		return new ResponseEntity<>(bookingServiceImpl.addUserBookingDetails(userDetails), HttpStatus.CREATED);
	} catch (Exception e){
		e.printStackTrace();
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	@DeleteMapping("/cancel/{pnrNo}")
	@ApiOperation(value = "Cancel a ticket")
	public ResponseEntity<HttpStatus> deleteUserDetailsById(@PathVariable long pnrNo) {
	restTemplate.getForObject("http://localhost:8085/pay/cancel/"+pnrNo, String.class);
		try {
		bookingServiceImpl.deleteUserBookingDetails(pnrNo);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch(Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

}