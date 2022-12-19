package com.RailwayReservationAdminManagement.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.RailwayReservationAdminManagement.controller.AdminController;
import com.RailwayReservationAdminManagement.model.TrainDetails;
import com.RailwayReservationAdminManagement.service.AdminService;
import com.RailwayReservationAdminManagement.service.AdminServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

//@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerMackitoTests {
	
//	@Mock
///	AdminService serv;
//	@InjectMocks
//	AdminController controller;
	
	@MockBean
	private AdminServiceImpl service;
	
	@Autowired
	MockMvc mockMvc;

	//List<TrainDetails> myusers;
   //TrainDetails user;

	
	@Test
	void test_UserServiceNotNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}
	
	
	@Test
	public void test_getAllUsers() throws Exception {
     TrainDetails p1 = new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
				"6hr 5mins", 12, 323, 456, 433, 556);

		List<TrainDetails> trains = new ArrayList<TrainDetails>();
		trains.add(p1);

		when(service.getAllDetails()).thenReturn(trains);
		mockMvc.perform(get("/admin/all"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$[*]", hasSize(1)))
		.andExpect(jsonPath("$[0].trainNo").value(12640))
		.andExpect(jsonPath("$[0].trainName").value("BRINDAVAN EXPRESS"))
		.andExpect(jsonPath("$[0].sourceStation").value("KSRBENGALURU"))
		.andExpect(jsonPath("$[0].destinationStation").value("MGRCHENNAICTL"))
		.andExpect(jsonPath("$[0].arrivalTime").value("21:05"))
		.andExpect(jsonPath("$[0].deptTime").value("15:00"))
		.andExpect(jsonPath("$[0].duration").value("6hr 5mins"))
		.andExpect(jsonPath("$[0].noOfSeats").value(12))
		.andExpect(jsonPath("$[0].firstClassACFare").value(323))
		.andExpect(jsonPath("$[0].twoTierAcFare").value(456))
		.andExpect(jsonPath("$[0].threeTierAcFare").value(433))
		.andExpect(jsonPath("$[0].sleeperFare").value(556));
		
	}
	
    @Test
	void test_getTrainById() throws Exception {
		TrainDetails user= new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
			"6hr 5mins", 12, 323, 456, 433, 556);
	when(service.getDetailsByTrainNo(12640)).thenReturn(user);
	mockMvc.perform(get("/admin/12640"))
	.andExpect(status().isOk())
	.andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$.trainNo").value(12640))
	.andExpect(jsonPath("$.trainName").value("BRINDAVAN EXPRESS"))
	.andExpect(jsonPath("$.sourceStation").value("KSRBENGALURU"))
	.andExpect(jsonPath("$.destinationStation").value("MGRCHENNAICTL"))
	.andExpect(jsonPath("$.arrivalTime").value("21:05"))
	.andExpect(jsonPath("$.deptTime").value("15:00"))
	.andExpect(jsonPath("$.duration").value("6hr 5mins"))
	.andExpect(jsonPath("$.noOfSeats").value(12))
	.andExpect(jsonPath("$.firstClassACFare").value(323))
	.andExpect(jsonPath("$.twoTierAcFare").value(456))
	.andExpect(jsonPath("$.threeTierAcFare").value(433))
	.andExpect(jsonPath("$.sleeperFare").value(556));	
//	}
//	
	/* @Test
	void test_delete() throws Exception {
		User user=new User(101,"MI-pro7","mobile",20000, null, null, null);
		String s="deleted successfully....";
	when(userServiceImpl.deleteById(101)).thenReturn(s);
	mockMvc.perform(delete("/delete/101"))
	.andExpect(status().isAccepted())
	.andExpect(content().string(s));	
	}
	
	*/ 
	
	
	
	
	
	
	/*@Test
	@Order(2)
	public void test_getUserById() throws UserDoesntExistException {
		myusers.add(new User(2, "ssz", "seg", 0, null, null, null));
		int userID=2;
		when(userServ.getUserById(userID)).thenReturn(user);
		Optional<Optional<User>> res= userController.getUserById(userID);
		assertEquals(,res.get)
		
	}*/
//	@Test
//	//@Order(2)
//	public void test_saveUser() throws Exception {
//		TrainDetails user = new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
//				"6hr 5mins", 12, 323, 456, 433, 556);
//	when(userServiceImpl.addTrainDetails(user)).thenReturn(user);
//	ResponseEntity<TrainDetails>res= userController.addTrainDetails(user);
//	assertEquals(HttpStatus.CREATED,res.getStatusCode());
//	assertEquals(user,res.getBody());
//	} 
//	
		 
	 }
}