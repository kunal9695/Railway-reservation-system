package com.RailwayReservationPaymentService.ControllerTest;
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

import com.RailwayReservationPaymentService.model.PaymentDetails;
import com.RailwayReservationPaymentService.service.PaymentServiceImpl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentServiceControllerTest {
	@MockBean
	private PaymentServiceImpl service;
	
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
		PaymentDetails p1=new PaymentDetails("8976435678908765", 2139348060, 543,"HDFC", "");
		
		List<PaymentDetails> productList=new ArrayList<PaymentDetails>();
		productList.add(p1);
		when(service.getAll()).thenReturn(productList);
	mockMvc.perform(get("/pay/all/"))
	.andExpect(status().isOk())
	.andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$[*]", hasSize(1)))
	.andExpect(jsonPath("$[0].cardNo").value("8976435678908765"))
	.andExpect(jsonPath("$[0].pnrNo").value(2139348060))
	.andExpect(jsonPath("$[0].cvv").value(543))
	.andExpect(jsonPath("$[0].bankName").value("HDFC"))
	.andExpect(jsonPath("$[0].classType").value(""));
	
	

	}
	
	
	
	@Test
	void testDeleteProductById() throws Exception {
		PaymentDetails p1=new PaymentDetails("8976435678908765", 2139348060, 543,"HDFC", "");
		String s="deleted successfully....";
	when(service.deletePayment(2139348060)).thenReturn(s);
	mockMvc.perform(delete("/pay/cancel/2139348060"))
	.andExpect(status().isOk())
	.andExpect(content().string(s));	
	}
	
	@Test
	void testdeleteProductInvalidId() throws Exception {
		PaymentDetails p1=new PaymentDetails("8976435678908765", 2139348060, 543,"HDFC", "");
		String s="deleted successfully....";
		when(service.deletePayment(2139348060)).thenReturn(s);
	MvcResult result=mockMvc.perform(delete("/pay/cancel/2675238060"))
	.andExpect(status().isOk())
	.andReturn();
	assertThat(result.getResponse().toString())
	.as("Product with the id 2675238060 doesn't exist");
		
	}
	
	@Test
	void testAddUser() throws Exception {
		PaymentDetails p1=new PaymentDetails("8976435678908765", 2139348060, 543,"HDFC", "");
		String s="Your payment is successful";
		when(service.proceedToPay(p1)).thenReturn(s);
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter  writer=mapper.writer().withDefaultPrettyPrinter();
		String reqstr=writer.writeValueAsString(p1);
	mockMvc.perform(post("/pay/add")
			.contentType("application/json")
			.content(reqstr))
	.andExpect(status().isOk())
	.andExpect(content().string(s));
		
	}
	

}