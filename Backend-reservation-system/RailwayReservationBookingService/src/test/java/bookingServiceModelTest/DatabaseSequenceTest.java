package bookingServiceModelTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RailwayReservationBookingService.model.DatabaseSequence;
import com.RailwayReservationBookingService.model.Passengers;
public class DatabaseSequenceTest {
	DatabaseSequence  t1;
	@BeforeEach
	public void before() {
		t1=new DatabaseSequence();
	}
	
	@AfterEach
	public void after() {
		t1=null;
	}
	
	@Test
	void testGetId() {
		String id = "1";
		t1.setId("1");
		assertEquals(id, t1.getId());
	}

	@Test
	void testGetSequence() {
		int seq=2;
		t1.setSeq(2);
		assertEquals(seq, t1.getSeq());
	}

	@Test
	void testSetId() {
		t1.setId("2");
		assertEquals("2", t1.getId());
	}
	@Test
	void testSetSequence() {
		t1.setSeq(3);
		assertEquals(3,t1.getSeq());
	}
/*	@Test
	public void testToString() {
	String a = "DatabaseSequence" ["id=" +id , + "seq=" seq];
}*/
}