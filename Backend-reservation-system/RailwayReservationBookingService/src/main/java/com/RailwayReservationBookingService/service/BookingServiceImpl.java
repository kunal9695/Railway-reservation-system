package com.RailwayReservationBookingService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.RailwayReservationBookingService.exception.ResourceNotFoundException;
import com.RailwayReservationBookingService.model.DatabaseSequence;
import com.RailwayReservationBookingService.model.UserDetails;
import com.RailwayReservationBookingService.repository.BookingRepository;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;

@Service
public class BookingServiceImpl implements BookingService {

	int id;
//	@Autowired
//	public EmailService emailService;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<UserDetails> getAll() {
		List<UserDetails> userDetails = new ArrayList<UserDetails>();
		bookingRepository.findAll().forEach(userDetails1 -> userDetails.add(userDetails1));
		System.out.println(userDetails);
		return userDetails;
	}

	@Override
	public UserDetails getUserDetailsById(long pnrNo) {
		List<UserDetails> userDetails = bookingRepository.findAll();
		for (UserDetails x : userDetails) {
			if (x.getPnrNo() == pnrNo) {
				id = x.getId();
			}
		}
		return bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No ticket is booked with PNR Number : " + pnrNo));
	}

	@Override
	public UserDetails addUserBookingDetails(UserDetails userDetails) {
		bookingRepository.save(userDetails);

//		try {
//			sendEmail(userDetails.getPnrNo());
//		} catch (AddressException e) {
//			e.printStackTrace();
//		}

//		return ("Your ticket id booked successfully...!!!  " + "Your pnr number is " + userDetails.getPnrNo()
//				+ " Please proceed to payment....");
		return userDetails;
	}

	@Override
	public String deleteUserBookingDetails(long pnrNo) {
		String msg = ("Your booking ticket with PNR Number : " + pnrNo + " is cancelled. "
				+ "Your payment amount will be credited to your account within 5 to 7 days..!!!");
		List<UserDetails> userDetails = bookingRepository.findAll();
		for (UserDetails x : userDetails) {
			if (x.getPnrNo() == pnrNo) {
				id = x.getId();
			}
		}
		UserDetails existingDetails = bookingRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Cannot delete as booking is not done with PNR Number : " + pnrNo));
		bookingRepository.delete(existingDetails);
//		try {
//			sendEmails(pnrNo);
//		} catch (AddressException e) {
//			e.printStackTrace();
//		}
		return msg;
	}

	/* To Get Autogenerated id */
	public int getSequenceNumber(String sequenceName) {
		/* Get Sequence No */
		Query query = new Query(Criteria.where("id").is(sequenceName));
		/* update the sequence no */
		Update update = new Update().inc("seq", 1);
		DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

//	/* To Send An Email After Booking Of A Train Ticket */
//	public void sendEmail(long pnrNo) throws AddressException {
//		String data1 = "Your train ticket booking is successful..!!";
//		String data2 = "Please Check the details....!!!!!!";
//		UserDetails userDet = getUserDetailsById(pnrNo);
//		final Email email = DefaultEmail.builder().from(new InternetAddress("suman1598@gmail.com"))
//				.replyTo(new InternetAddress("suman1598@gmail.com"))
//				.to(Lists.newArrayList(new InternetAddress("suman1598@gmail.com")))
//				.subject("Your ticket is booked").body(data1 + "\n" + data2 + "\n" + userDet).encoding("UTF-8").build();
//		emailService.send(email);
//	}
//
//	/* For Email Notification After Cancelled Payment */
//	public void sendEmails(long pnrNo) throws AddressException {
//		final Email email = DefaultEmail.builder().from(new InternetAddress("suman1598@gmail.com"))
//				.replyTo(new InternetAddress("suman1598@gmail.com"))
//				.to(Lists.newArrayList(new InternetAddress("suman1598@gmail.com")))
//				.subject("Your ticket is Cancelled")
//				.body("Your booking ticket with PNR Number : " + pnrNo + " is cancelled. "
//						+ "Your payment amount will be credited to your account within 5 to 7 days..!!!")
//				.encoding("UTF-8").build();
//		emailService.send(email);
//	}
}