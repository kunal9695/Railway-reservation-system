package com.RailwayReservationBookingService;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.RailwayReservationBookingService.model.UserDetails;
import com.RailwayReservationBookingService.repository.BookingRepository;
import com.RailwayReservationBookingService.service.BookingService;




//@RunWith(SpringRunner.class)

@SpringBootTest
class RailwayReservationBookingServiceApplicationTests {

	
	@Autowired
	private BookingService bookingService;
	@MockBean
	private BookingRepository bookingRepository;
	
	

	@Test
	public void getallUsersTest() {
		UserDetails userDetails = new UserDetails(1,2139348060,"Aayushi", 23, "Female", "Madhubani", 12163, "Chennai Express", "Kashmir", "Kanyakumari","firstClassAcFare", 2, 0, "Successful");
		bookingService.getAll();
		verify(bookingRepository, times(1)).findAll();
	}
	 @Test
	    public void getUserById() {
		   UserDetails  f = new UserDetails(1,2139348060,"Aayushi", 23, "Female", "Madhubani", 12163, "Chennai Express", "Kashmir", "Kanyakumari","firstClassAcFare", 2, 0, "Successful");
	       when(bookingRepository.findById(anyInt())).thenReturn(Optional.of(f));
	       UserDetails existingTrains = bookingService.getUserDetailsById(2139348060);
		   assertNotNull(existingTrains);
		   assertThat(existingTrains.getId()).isEqualTo(1);
	}

	    @Test
	    public void deleteUser() {
	    UserDetails  f = new UserDetails(1,2139348060,"Aayushi", 23, "Female", "Madhubani", 12163, "Chennai Express", "Kashmir", "Kanyakumari","firstClassAcFare", 2, 0, "Successful");
		
		when(bookingRepository.findById(anyInt())).thenReturn(Optional.of(f));
	   doNothing().when(bookingRepository).delete(any(UserDetails.class));
	    
	   bookingService.deleteUserBookingDetails(2139348060);
	   verify(bookingRepository, times(1)).deleteById(1);
	 }
	@Test
	public void addUsersTest() {
		UserDetails userDetails = new UserDetails(1,2139348060,"Aayushi", 23, "Female", "Madhubani", 12163, "Chennai Express", "Kashmir", "Kanyakumari","firstClassAcFare", 2, 0, "Successful");
		bookingService.addUserBookingDetails(userDetails);
		verify(bookingRepository, times(1)).save(userDetails);
	}
	
}