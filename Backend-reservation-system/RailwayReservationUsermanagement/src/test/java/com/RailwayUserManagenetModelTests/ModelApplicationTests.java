package com.RailwayUserManagenetModelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RailwayUserManagenet.model.UserSignUp;


public class ModelApplicationTests {
	UserSignUp p1;
	@BeforeEach
	public void before() {
		p1=new UserSignUp(123, "Shuaib@gmail.com", 9895675645l, "shuaib123", "54321",
				"54321");
	}
	
	
	@AfterEach
	public void after() {
		p1=null;
	}
	
	@Test
	void testGetId() {
		assertEquals(123, p1.getId());
	}

	@Test
	void testGetEmailId() {
		assertEquals("Shuaib@gmail.com", p1.getEmailId());
	}

	@Test
	void testGetContact() {
		assertEquals(9895675645l, p1.getContact());
	}

	@Test
	void testGetUsername() {
		assertEquals("shuaib123", p1.getUsername());
	}
	@Test
	void testGetPassword() {
		assertEquals("54321", p1.getPassword());
	}
	@Test
	void testGetConfirmPassword() {
		assertEquals("54321", p1.getConfirmpassword());
	}
	
	@Test
	void testSetId() {
		p1.setId(111);
		assertEquals(111, p1.getId());
	}

	@Test
	void testSetEmail() {
		p1.setEmailId("prateek@gmail.com");
		assertEquals("prateek@gmail.com", p1.getEmailId());
	}

	@Test
	void testSetContact() {
		p1.setContact(9857450912L);
		assertEquals(9857450912L,p1.getContact());
	}

	@Test
	void testSetUsername() {
		p1.setUsername("Prateek");
		assertEquals("Prateek", p1.getUsername());
	}
	@Test
	void testSetPassword() {
		p1.setPassword("12345");
		assertEquals("12345", p1.getPassword());
	}
	@Test
	void testSetConfirmPassword() {
		p1.setConfirmpassword("12345");
		assertEquals("12345", p1.getConfirmpassword());
	}
	
}
