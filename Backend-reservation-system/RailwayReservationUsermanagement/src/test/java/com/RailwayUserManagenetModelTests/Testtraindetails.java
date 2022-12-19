package com.RailwayUserManagenetModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RailwayUserManagenet.model.TrainDetails;


public class Testtraindetails {
	
		TrainDetails  t1;
		@BeforeEach
		public void before() {
			t1=new TrainDetails(12246,"Duronto Express","Kolkata","Bengaluru","16:00", "11:15", "28 hours 45 minutes", 490, 4000, 3500, 3000,2500);
		}
		
		@AfterEach
		public void after() {
			t1=null;
		}
		
		@Test
		void testGetTrainId() {
			assertEquals(12246, t1.getTrainNo());
		}

		@Test
		void testGetTrainname() {
			assertEquals("Duronto Express", t1.getTrainName());
		}

		@Test
		void testGetSourceStation() {
			assertEquals("Kolkata", t1.getSourceStation());
		}

		@Test
		void testGetdestinationStation() {
			assertEquals("Bengaluru", t1.getDestinationStation());
		}
		@Test
		void testGetarrivalTime() {
			assertEquals("16:00", t1.getArrivalTime());
		}
		@Test
		void testGetduration() {
			assertEquals("28 hours 45 minutes", t1.getDuration());
		}
		@Test
		void testGetnoOfSeats() {
			assertEquals(490, t1.getNoOfSeats());
		}
		@Test
		void testGetfirstClassACFare() {
			assertEquals(4000, t1.getFirstClassACFare());
		}
		@Test
		void testGettwoTierAcFare() {
			assertEquals(3500, t1.getTwoTierAcFare());
		}
		@Test
		void testGetthreeTierAcFare() {
			assertEquals(3000, t1.getThreeTierAcFare());
		}
		@Test
		void testGetsleeperFare() {
			assertEquals(2500, t1.getSleeperFare());
		}
		
		
		@Test
		void testSetTrainId() {
			t1.setTrainNo(12161);
			assertEquals(12161, t1.getTrainNo());
		}
		@Test
		void testSetTrainname() {
			t1.setTrainName("Karnataka Express");
			assertEquals("Karnataka Express",t1.getTrainName());
		}
		@Test
		void testSetSourceStation() {
			t1.setSourceStation("Bangalore");;
			assertEquals("Bangalore",t1.getSourceStation());
		}

		@Test
		void testSetDestinationStation() {
			t1.setDestinationStation("Anantapur");
			assertEquals("Anantapur",t1.getDestinationStation());
		}

		@Test
		void testSetarrivalTime() {
			t1.setArrivalTime("19:30");
			assertEquals("19:30", t1.getArrivalTime());
		}
		@Test
		void testSetDeptTime() {
			t1.setDeptTime("23:30");
			assertEquals("23:30",t1.getDeptTime());
		}
		@Test
		void testSetduration() {
	        t1.setDuration("4 hours 0 minutes");
			assertEquals("4 hours 0 minutes",t1.getDuration());
		}
		@Test
		void testSetnoofseats() {
			t1.setNoOfSeats(500);
			assertEquals(500,t1.getNoOfSeats());
		}
		@Test
		void testSetfirstclassAcFare(){
			t1.setFirstClassACFare(1500);
			assertEquals(1500,t1.getFirstClassACFare());
		}
		@Test
		void testSetsecondclassAcfare() {
			t1.setTwoTierAcFare(1000);
			assertEquals(1000, t1.getTwoTierAcFare());
		}
		@Test
		void testSetthirdclassacfare() {
			t1.setThreeTierAcFare(500);
			assertEquals(500, t1.getThreeTierAcFare());
		}
		@Test
		void testSetsleeperfare() {
	        t1.setSleeperFare(200);
			assertEquals(200,t1.getSleeperFare());
		}
	}