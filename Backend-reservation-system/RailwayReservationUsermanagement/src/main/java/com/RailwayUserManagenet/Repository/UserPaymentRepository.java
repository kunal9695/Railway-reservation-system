package com.RailwayUserManagenet.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.RailwayUserManagenet.model.PaymentDetails;

/**
 * This Interface Is Used For Adding MongoRepository Which Acts As A Database For User Payment
 */
@Repository
public interface UserPaymentRepository extends MongoRepository<PaymentDetails, Long> {

}
