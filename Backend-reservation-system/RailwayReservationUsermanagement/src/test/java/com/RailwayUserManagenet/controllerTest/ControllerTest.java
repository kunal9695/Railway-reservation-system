package com.RailwayUserManagenet.controllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.RailwayUserManagenet.Repository.UserSignUpRepository;
import com.RailwayUserManagenet.controller.UserManagementController;
import com.RailwayUserManagenet.model.TrainDetails;
import com.RailwayUserManagenet.model.UserSignUp;
import com.RailwayUserManagenet.service.SignUpService;
import com.RailwayUserManagenet.service.UserServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import com.fasterxml.jackson.databind.ObjectMapper;




@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	@InjectMocks
	private UserManagementController controller;

	@MockBean
	private UserServiceImpl service;
	
	@MockBean
	private SignUpService service1;
	
	@MockBean
	private UserSignUpRepository flightrepository;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testServiceNotNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}
	
	@Test
	void testShowTrains() throws Exception {
		TrainDetails p1=new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
				"6hr 5mins", 120, 323, 456, 433, 556);
	//	TrainDetails p2 =new TrainDetails(11014, "KURLA EXPRESS", "KSRBENGALURU", "LMTMUM" ,"16:00", "14:30",
	//					"22hr 30mins", 100, 380, 480, 430, 550);
		List<TrainDetails> productList=new ArrayList<TrainDetails>();
		productList.add(p1);
	//	productList.add(p2);
		when(service.getAllDetails()).thenReturn(productList);
	mockMvc.perform(get("/users/alltrains"))
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
	.andExpect(jsonPath("$[0].noOfSeats").value(120))
	.andExpect(jsonPath("$[0].firstClassACFare").value(323))
	.andExpect(jsonPath("$[0].twoTierAcFare").value(456))
	.andExpect(jsonPath("$[0].threeTierAcFare").value(433))
	.andExpect(jsonPath("$[0].sleeperFare").value(556));
	
	}
	
	@Test
	void testShowUsers() throws Exception  {
	UserSignUp p1=new UserSignUp(123, "Shuaib@gmail.com", 9895675645l, "shuaib123", "54321","54321");
	//	TrainDetails p2 =new TrainDetails(11014, "KURLA EXPRESS", "KSRBENGALURU", "LMTMUM" ,"16:00", "14:30",
	//					"22hr 30mins", 100, 380, 480, 430, 550);
		List<UserSignUp> productList=new ArrayList<UserSignUp>();
		productList.add(p1);
	//	productList.add(p2);
		when(service1.getuser()).thenReturn(productList);
	mockMvc.perform(get("/users/allusers"))
	.andExpect(status().isOk())
	.andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$[*]", hasSize(1)))
	.andExpect(jsonPath("$[0].id").value(123))
	.andExpect(jsonPath("$[0].emailId").value("Shuaib@gmail.com"))
	.andExpect(jsonPath("$[0].contact").value(9895675645l))
	.andExpect(jsonPath("$[0].username").value("shuaib123"))
	.andExpect(jsonPath("$[0].password").value("54321"))
	.andExpect(jsonPath("$[0].confirmpassword").value("54321"));
	}
	
 /*   @Test
	@WithMockUser(username = "test", password = "test", roles = "USER")
	public void getTrainsById() throws Exception {
		TrainDetails f = new TrainDetails();
		f.setTrainNo(12246);
		f.setTrainName("Duronto Express");
		f.setSourceStation("Kolkata");
		f.setDestinationStation("Bengaluru");
		f.setArrivalTime("16:00");
		f.setDeptTime("11:15");
		f.setDuration("28 hours 45 minutes");
		f.setNoOfSeats(490);
        f.setFirstClassACFare(4000);
        f.setTwoTierAcFare(3500);
        f.setThreeTierAcFare(3000);
        f.setSleeperFare(2500);
        
		when(service.getDetailsByTrainNo(12246)).thenReturn(f);
		String url = "/users/12246";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		String exceptedJsonResponse = objectMapper.writeValueAsString(f);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);
	}*/

    @Test
	void GetTrainById() throws Exception {
	TrainDetails p1=new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
				"6hr 5mins", 120, 323, 456, 433, 556);
	when(service.getDetailsByTrainNo(12640)).thenReturn(p1);
	mockMvc.perform(get("/users/show/12640"))
	.andDo(print())  
//	.andExpect(status().isOk())
    .andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$[*]", hasSize(1)))
	.andExpect(jsonPath("$[0].trainNo").value(12640))
		.andExpect(jsonPath("$[0].trainName").value("BRINDAVAN EXPRESS"))
		.andExpect(jsonPath("$[0].sourceStation").value("KSRBENGALURU"))
	.andExpect(jsonPath("$[0].destinationStation").value("MGRCHENNAICTL"))
		.andExpect(jsonPath("$[0].arrivalTime").value("21:05"))
		.andExpect(jsonPath("$[0].deptTime").value("15:00"))
		.andExpect(jsonPath("$[0].duration").value("6hr 5mins"))
		.andExpect(jsonPath("$[0].noOfSeats").value(120))
		.andExpect(jsonPath("$[0].firstClassACFare").value(323))
	.andExpect(jsonPath("$[0].twoTierAcFare").value(456))
		.andExpect(jsonPath("$[0].threeTierAcFare").value(433))
		.andExpect(jsonPath("$[0].sleeperFare").value(556))
		.andReturn().getResponse().getContentAsString();
	
		}

	@Test
	void testShowTrainsInvalidId() throws Exception {
	TrainDetails p1=new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
				"6hr 5mins", 120, 323, 456, 433, 556);
		when(service.getDetailsByTrainNo(12640)).thenReturn(p1);
		MvcResult result=mockMvc.perform(get("/users/12158"))
//		.andExpect(status().isOk())
		.andReturn();
		assertThat(result.getResponse().toString())
		.as("Product with the id 12158 doesn't exist");
		
	}

	
/*	@Test
	void GetTrainByStartandDestinationStation() throws Exception {
		TrainDetails p1=new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
				"6hr 5mins", 120, 323, 456, 433, 556);
	//	TrainDetails p2 =new TrainDetails(11014, "KURLA EXPRESS", "KSRBENGALURU", "LMTMUM" ,"16:00", "14:30",
	//							"22hr 30mins", 100, 380, 480, 430, 550);
		List<TrainDetails> productList=new ArrayList<TrainDetails>();
		productList.add(p1);
		
	when(service.getTrainDetailsByEndPoints("KSRBENGALURU","MGRCHENNAICTL")).thenReturn(productList);
	mockMvc.perform(get("/users/12640"))
	.andDo(print()) 
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
	.andExpect(jsonPath("$[0].noOfSeats").value(120))
	.andExpect(jsonPath("$[0].firstClassACFare").value(323))
	.andExpect(jsonPath("$[0].twoTierAcFare").value(456))
	.andExpect(jsonPath("$[0].threeTierAcFare").value(433))
	.andExpect(jsonPath("$[0].sleeperFare").value(556));
	
	}
	*/
}