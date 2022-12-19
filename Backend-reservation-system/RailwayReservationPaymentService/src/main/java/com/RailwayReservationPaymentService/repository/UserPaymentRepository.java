package com.RailwayReservationPaymentService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.RailwayReservationPaymentService.model.PaymentDetails;

/*
 * Repository For Connecting MongoDb With PaymentDetails Model
 */
@Repository
public interface UserPaymentRepository extends MongoRepository<PaymentDetails, Long> {

}
