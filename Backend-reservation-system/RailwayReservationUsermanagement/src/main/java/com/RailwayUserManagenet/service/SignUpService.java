package com.RailwayUserManagenet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.RailwayUserManagenet.Repository.UserSignUpRepository;
import com.RailwayUserManagenet.model.UserSignUp;

/**
 * This Class Is Used As A Service Where You Can
 *  Explicitly Execute Methods For Controllers
 */
@Service
public class SignUpService {
	/* Autowired User SignUp Repository */
	@Autowired
	private UserSignUpRepository userSignUpRepository;

	/* Method For Adding Data 
	 * @return */
	public UserSignUp addUser(UserSignUp userSignUp) {
		return userSignUpRepository.save(userSignUp);
	}

	/* Method For Listing The Data */
	public List<UserSignUp> getuser() {
		List<UserSignUp> s = userSignUpRepository.findAll();
		return s;
	}

	/* Method For Updating The Data */
	public UserSignUp updateUser(int id, UserSignUp userSignUp) {
		return userSignUpRepository.save(userSignUp);
	}

	/* Method For Deleting The Data */
	public void deleteUser(int id) {
		userSignUpRepository.deleteById(id);
	}

}