package com.RailwayReservationPaymentServiceModelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RailwayReservationPaymentService.exception.ErrorDetails;


public class Errordetails {
	ErrorDetails  d1;
	@BeforeEach
	public void before() {
		d1=new ErrorDetails(new Date(26, 10, 2022),"sent","delivered");
	}
	
	@AfterEach
	public void after() {
		d1=null;
	}
	
	@Test
	void testGetDate() {
		assertEquals(new Date(26, 10, 2022),d1.getTimestamp());
	}

	@Test
	void testGetMessage() {
		assertEquals("sent",d1.getMessage());
	}

	@Test
	void testGetDetails() {
		assertEquals("delivered",d1.getDetails());
	}
    @Test
	void testSetDate() {
	    d1.setTimestamp(new Date(24,11,2023));
		assertEquals(new Date(24,11,2023),d1.getTimestamp());
	}
	@Test
	void testSetMessage() {
		d1.setMessage("packed");
		assertEquals("packed",d1.getMessage());
	}
	@Test
	void testSetDetails() {
		d1.setDetails("sent to nearby store");
		assertEquals("sent to nearby store",d1.getDetails());
	}
}