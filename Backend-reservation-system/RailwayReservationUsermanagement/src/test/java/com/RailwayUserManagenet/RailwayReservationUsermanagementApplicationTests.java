package com.RailwayUserManagenet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.RailwayUserManagenet.model.TrainDetails;
import com.RailwayUserManagenet.Exception.ResourceNotFoundException;
import com.RailwayUserManagenet.Repository.UserRepository;
import com.RailwayUserManagenet.Repository.UserSignUpRepository;
import com.RailwayUserManagenet.model.UserSignUp;
import com.RailwayUserManagenet.service.SignUpService;
import com.RailwayUserManagenet.service.UserService;
import static org.assertj.core.api.Assertions.assertThat;


//@RunWith(SpringRunner.class)
@SpringBootTest
class RailwayReservationUsermanagementApplicationTests {

	@Autowired
	private SignUpService signUpService;
	@MockBean
	private UserSignUpRepository userSignUpRepository;

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void addUsersTest() {
		UserSignUp userDetails = new UserSignUp(123, "Shuaib@gmail.com", 9895675645l, "shuaib123", "54321",
				"54321");
			signUpService.addUser(userDetails);
			verify(userSignUpRepository, times(1)).save(userDetails);
		
	}

    @Test
	public void getAllTrainsTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(
				new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
						"6hr 5mins", 12, 323, 456, 433, 556),
				new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
						"6hr 5mins", 12, 344, 322, 544, 33))
				.collect(Collectors.toList()));
		assertEquals(2, userService.getAllDetails().size());
	}
	@Test
    public void getUserTest() {
		when(userSignUpRepository.findAll()).thenReturn(Stream.of(
				new UserSignUp(123, "Shuaib@gmail.com", 9895675645l, "shuaib123", "54321",
						"54321"),
				new UserSignUp(122, "Steyn@gmail.com", 9895636642l, "steyn123", "12345",
						"12345"))
				.collect(Collectors.toList()));
		assertEquals(2,signUpService.getuser().size());
	}	
	
    @Test
    public void getTrainById() {
	   TrainDetails  f = new TrainDetails();
	   f.setTrainNo(12640);
	   f.setTrainName("BRINDAVAN EXPRESS");
	   f.setSourceStation("KSRBENGALURU");
	   f.setDestinationStation("MGRCHENNAICTL");
	   f.setArrivalTime("21:05");
	   f.setDeptTime("15:00");
	   f.setDuration("6hr 5mins");
	   f.setNoOfSeats(120);
	   f.setFirstClassACFare(5000);
	   f.setTwoTierAcFare(4500);
	   f.setThreeTierAcFare(3500);
	   f.setSleeperFare(2000);
       when(userRepository.findById(anyInt())).thenReturn(Optional.of(f));
       TrainDetails existingTrains = userService.getDetailsByTrainNo(12640);
	
	   assertNotNull(existingTrains);
	   assertThat(existingTrains.getTrainNo()).isEqualTo(12640);
}

/*@Test
public void getTrainByInvalidId() {
	TrainDetails  f = new TrainDetails();
	f.setTrainNo(12640);
	f.setTrainName("BRINDAVAN EXPRESS");
	f.setSourceStation("KSRBENGALURU");
	f.setDestinationStation("MGRCHENNAICTL");
	f.setArrivalTime("21:05");
	f.setDeptTime("15:00");
	f.setDuration("6hr 5mins");
	f.setNoOfSeats(120);
	f.setFirstClassACFare(5000);
	f.setTwoTierAcFare(4500);
	f.setThreeTierAcFare(3500);
	f.setSleeperFare(2000);
    when(userRepository.findById(anyInt())).thenReturn(Optional.of(f));
    assertThrows(RuntimeException.class, () ->{
	userService.getDetailsByTrainNo(12640);
});*/

    @Test
    public void updateUser () {
	UserSignUp u = new UserSignUp();
			u.setId(123);
			u.setEmailId("Shuaib@gmail.com");
			u.setContact(9895675645l);
			u.setUsername("shuaib123");
			u.setPassword("54321");
			u.setConfirmpassword("54321");
    when(userSignUpRepository.findById(anyInt())).thenReturn(Optional.of(u));
    when(userSignUpRepository.save(any(UserSignUp.class))).thenReturn(u);
	u.setUsername("shoaib123");
	
	UserSignUp updateuser = signUpService.updateUser(123, u);
	
	assertNotNull(updateuser);
	assertEquals("shoaib123", updateuser.getUsername());
	
	
}
    @Test
    public void deleteUser() {
	UserSignUp userDetails = new UserSignUp(123, "Shuaib@gmail.com", 9895675645l, "shuaib123", "54321",
			"54321");
	
	when(userSignUpRepository.findById(anyInt())).thenReturn(Optional.of(userDetails));
   doNothing().when(userSignUpRepository).delete(any(UserSignUp.class));
    
   signUpService.deleteUser(123);
   verify(userSignUpRepository, times(1)).findById(123);
   verify(userSignUpRepository, times(1)).deleteById(123);
 }
 /*   @Test
    public void getTrainByEndPoints() {
	   TrainDetails  f = new TrainDetails();
	   f.setTrainNo(12640);
	   f.setTrainName("BRINDAVAN EXPRESS");
	   f.setSourceStation("KSRBENGALURU");
	   f.setDestinationStation("MGRCHENNAICTL");
	   f.setArrivalTime("21:05");
	   f.setDeptTime("15:00");
	   f.setDuration("6hr 5mins");
	   f.setNoOfSeats(120);
	   f.setFirstClassACFare(5000);
	   f.setTwoTierAcFare(4500);
	   f.setThreeTierAcFare(3500);
	   f.setSleeperFare(2000);
       when(userRepository.findById(anyInt())).thenReturn(Optional.of(f));
       List<TrainDetails> existingTrains = userService.getTrainDetailsByEndPoints("KSRBENGALURU","MGRCHENNAICTL" );
	
	   assertNotNull(existingTrains);
	   assertThat(existingTrains.get(0).getSourceStation().equals("KSRBENGALURU"));
}
*/
}