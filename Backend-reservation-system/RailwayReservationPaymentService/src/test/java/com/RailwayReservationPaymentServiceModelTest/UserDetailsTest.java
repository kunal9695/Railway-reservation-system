package com.RailwayReservationPaymentServiceModelTest;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RailwayReservationPaymentService.model.UserDetailsData;

public class UserDetailsTest {
	UserDetailsData  t1;
	@BeforeEach
	public void before() {
		t1=new UserDetailsData(0,3253878363L,"Aayushi",23,"Female","Madhubani",12163,"Chennai Express","Kashmir","Kanyakumari","firstClassAcFare",2,0, "Successful");
	}
	
	@AfterEach
	public void after() {
		t1=null;
	}
	@Test
	void testGetId() {
		assertEquals(0, t1.getId());
	}
	@Test
	void testGetPnrNo() {
		assertEquals(3253878363L, t1.getPnrNo());
	}
	@Test
	void testGetName() {
		assertEquals("Aayushi", t1.getName());
	}
	@Test
	void testGetAge() {
		assertEquals(23, t1.getAge());
	}
	@Test
	void testGetSex() {
		assertEquals("Female", t1.getSex());
	}
	@Test
	void testGetAddress() {
		assertEquals("Madhubani", t1.getAddress());
	}
	
	@Test
	void testGetTrainId() {
		assertEquals(12163, t1.getTrainNo());
	}
	
	@Test
	void testGetTrainname() {
		assertEquals("Chennai Express", t1.getTrainName());
	}

	@Test
	void testGetSourceStation() {
		assertEquals("Kashmir",t1.getSourceStation());
	}

	@Test
	void testGetdestinationStation() {
		assertEquals("Kanyakumari",t1.getDestinationStation());
	}
	@Test
	void testGetClassType() {
		assertEquals("firstClassAcFare", t1.getClassType());
	}
	@Test
	void testGetAdults() {
		assertEquals(2, t1.getAdults());
	}
	@Test
	void testGetChildren() {
		assertEquals(0, t1.getChildren());
	}
	@Test
	void testGetPayment() {
		assertEquals("Successful", t1.getPayment());
	}
	
	
	@Test
	void testSetId() {
		t1.setTrainNo(12161);
		assertEquals(12161, t1.getTrainNo());
	}
	
	@Test
	void testSetName() {
		t1.setName("Prateek");;
		assertEquals("Prateek",t1.getName());
	}

	@Test
	void testSetAge() {
		t1.setAge(21);
		assertEquals(21,t1.getAge());
	}

	@Test
	void testSetSex() {
		t1.setSex("Male");;
		assertEquals("Male",t1.getSex());
	}
	@Test
	void testSetAddress() {
		t1.setAddress("Andheri");
		assertEquals("Andheri",t1.getAddress());
	}
	@Test
	void testSetTrainNo() {
        t1.setTrainNo(11014);
		assertEquals(11014,t1.getTrainNo());
	}
	@Test
	void testSetTrainName() {
		t1.setTrainName("Kurla Express");
		assertEquals("Kurla Express",t1.getTrainName());
	}
	@Test
	void testSetSourceStation(){
		t1.setSourceStation("Anantapur");
		assertEquals("Anantapur",t1.getSourceStation());
	}
	@Test
	void testSetDestinationStation() {
		t1.setDestinationStation("Mumbai");
		assertEquals("Mumbai", t1.getDestinationStation());
	}

	@Test
	void testSetClassType() {
        t1.setClassType("ThirdClassAC");
		assertEquals("ThirdClassAC",t1.getClassType());
	}
	@Test
	void testSetAdults() {
        t1.setAdults(2);;
		assertEquals(2,t1.getAdults());
	}
	@Test
	void testSetChildren() {
        t1.setChildren(2);;
		assertEquals(2,t1.getChildren());
	}
	@Test
	void testSetPayment() {
        t1.setPayment("Successfull");
		assertEquals("Successfull",t1.getPayment());
	}
}