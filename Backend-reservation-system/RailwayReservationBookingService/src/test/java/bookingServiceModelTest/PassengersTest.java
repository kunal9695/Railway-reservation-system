package bookingServiceModelTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RailwayReservationBookingService.model.Passengers;

public class PassengersTest {
	Passengers  t1;
	@BeforeEach
	public void before() {
		t1=new Passengers(2,2);
	}
	
	@AfterEach
	public void after() {
		t1=null;
	}
	
	@Test
	void testGetAdults() {
		assertEquals(2, t1.getAdults());
	}

	@Test
	void testGetChildren() {
		assertEquals(2, t1.getChildren());
	}

	@Test
	void testSetAdults() {
		t1.setAdults(2);
		assertEquals(2, t1.getAdults());
	}
	@Test
	void testSetTrainname() {
		t1.setChildren(1);
		assertEquals(1,t1.getChildren());
	}
}