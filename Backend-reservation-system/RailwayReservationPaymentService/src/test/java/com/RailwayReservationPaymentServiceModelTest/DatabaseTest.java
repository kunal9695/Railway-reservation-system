package com.RailwayReservationPaymentServiceModelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.RailwayReservationPaymentService.model.DatabaseSequence;

public class DatabaseTest {
	DatabaseSequence  d1;
	@BeforeEach
	public void before() {
		d1=new DatabaseSequence();
	}
	
	@AfterEach
	public void after() {
		d1=null;
	}
	
	
	@Test
	void testSetSeq() {
	    d1.setSeq(5678);
	}
	@Test
	void testSetId() {
		d1.setId("2344674541");
		assertEquals("2344674541",d1.getId());
	}
	
}