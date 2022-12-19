package com.RailwayUserManagenetModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RailwayUserManagenet.model.PaymentDetails;


public class PaymentModelTest {
	PaymentDetails  d1;
	@BeforeEach
	public void before() {
		d1=new PaymentDetails("8976435678908765", 2139348060, 543,"HDFC", "");
	}
	
	@AfterEach
	public void after() {
		d1=null;
	}
	
	@Test
	void testGetcardNo() {
		assertEquals("8976435678908765",d1.getCardNo());
	}

	@Test
	void testGetpnrNo() {
		assertEquals(2139348060,d1.getPnrNo());
	}

	@Test
	void testGetCvv() {
		assertEquals(543,d1.getCvv());
	}

	@Test
	void testGetBankName() {
		assertEquals("HDFC", d1.getBankName());
	}
	@Test
	void testGetClassType() {
		assertEquals("", d1.getClassType());
	}


	@Test
	void testSetcardNo() {
	    d1.setCardNo("56786209847282938");
		assertEquals("56786209847282938",d1.getCardNo());
	}
	@Test
	void testSetpnrNo() {
		d1.setPnrNo(2344674541L);;
		assertEquals(2344674541L,d1.getPnrNo());
	}
	@Test
	void testSetcvv() {
		d1.setCvv(647);
		assertEquals(647,d1.getCvv());
	}

	@Test
	void testSetBankName() {
		d1.setBankName("Union Bank");;
		assertEquals("Union Bank",d1.getBankName());
	}

	@Test
	void testSetClassType() {
		d1.setClassType("");
		assertEquals("",d1.getClassType());
	}
	
}