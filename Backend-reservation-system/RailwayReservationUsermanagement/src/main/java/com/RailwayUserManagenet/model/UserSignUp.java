package com.RailwayUserManagenet.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userSignUpDetails")
public class UserSignUp {

	@Id
	@NotNull(message=" The User ID is required")
	private int id;
	
	@NotBlank(message="email is required")
	@Size(max = 40)
	@Email(message="invalid email")
	private String emailId;
	
	@NotBlank(message="phone number is required")
	@Size(max=10)
	@Size(min=10)
	private Long contact;
	
	@NotBlank(message="username is required")
	@Size(max = 40)
	private String username;
	
	@NotBlank(message="password is required")
	@Size(max=10)
	@Size(min=10)
	private String password;
	
	@NotBlank(message="password is required")
	@Size(max=10)
	@Size(min=10)
	private String confirmpassword;

	public UserSignUp() {
		super();
	}

	public UserSignUp(int id, String emailId, Long contact, String username, String password,
			String confirmpassword) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.contact = contact;
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	@Override
	public String toString() {
		return "UserSignUp [id=" + id + ", emailId=" + emailId + ", contact=" + contact
				+ ", username=" + username + ", password=" + password + ", confirmpassword=" + confirmpassword + "]";
	}

}