
package com.RailwayReservationBookingService.controllerTest;
import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import com.RailwayReservationBookingService.model.UserDetails;
import com.RailwayReservationBookingService.service.BookingServiceImpl;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	@MockBean
	private BookingServiceImpl service;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	void testServiceNotNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}
	
	
	@Test
	void testShowUsers() throws Exception {
		UserDetails p1=new UserDetails(0,3253878363L,"Aayushi",23,"Female","Madhubani",12163,"Chennai Express","Kashmir","Kanyakumari","firstClassAcFare",2,0, "Successful");
		
		List<UserDetails> productList=new ArrayList<UserDetails>();
		productList.add(p1);
		when(service.getAll()).thenReturn(productList);
	mockMvc.perform(get("/user/all/"))
	.andExpect(status().isOk())
	.andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$[*]", hasSize(1)))
	.andExpect(jsonPath("$[0].id").value(0))
	.andExpect(jsonPath("$[0].pnrNo").value(3253878363L))
	.andExpect(jsonPath("$[0].name").value("Aayushi"))
	.andExpect(jsonPath("$[0].age").value(23))
	.andExpect(jsonPath("$[0].sex").value("Female"))
	.andExpect(jsonPath("$[0].address").value("Madhubani"))
	.andExpect(jsonPath("$[0].trainNo").value(12163))
	.andExpect(jsonPath("$[0].trainName").value("Chennai Express"))
	.andExpect(jsonPath("$[0].sourceStation").value("Kanyakumari"))
//	.andExpect(jsonPath("$[0].destinationStation").value("Kashmir"))
	.andExpect(jsonPath("$[0].classType").value("firstClassAcFare"))
	.andExpect(jsonPath("$[0].adults").value(2))
	.andExpect(jsonPath("$[0].children").value(0))
	.andExpect(jsonPath("$[0].payment").value("Successful"));
	}
}