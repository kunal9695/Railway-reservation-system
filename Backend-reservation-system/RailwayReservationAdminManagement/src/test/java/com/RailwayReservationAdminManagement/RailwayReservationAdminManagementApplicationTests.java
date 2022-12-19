package com.RailwayReservationAdminManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.RailwayReservationAdminManagement.model.TrainDetails;
import com.RailwayReservationAdminManagement.repository.AdminRepository;
import com.RailwayReservationAdminManagement.service.AdminService;



//@RunWith(SpringRunner.class)
@SpringBootTest
class RailwayReservationAdminManagementApplicationTests {

	@Autowired
	private AdminService adminService;
	@MockBean
	private AdminRepository adminRepository;

	@Test
	public void addTrainsTest() {
		TrainDetails trainDetails = new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL",
				"21:05", "15:00", "6hr 5mins", 12, 323, 456, 433, 556);
		adminService.addTrainDetails(trainDetails);
		verify(adminRepository, times(1)).save(trainDetails);
	}

	@Test
	public void getAllTrainsTest() {
		when(adminRepository.findAll()).thenReturn(Stream.of(
				new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
						"6hr 5mins", 12, 323, 456, 433, 556),
				new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL", "21:05", "15:00",
						"6hr 5mins", 12, 344, 322, 544, 33))
				.collect(Collectors.toList()));
		assertEquals(2, adminService.getAllDetails().size());
	}

	
//    @Test 
//    public void updateTrainDetailsTest() { 
//	   int rtrainNo = 12650;
//	  TrainDetails trainDetails =new TrainDetails(12650,"BRINDAVAN EXPRESS","KSRBENGALURU","MGRCHENNAICTL","21:05"
//	  ,"15:00","6hr 5mins",12,323,456,433,556);
//	  adminService.updateTrainDetails(rtrainNo,trainDetails);
//	  verify(adminRepository, times(1)).save(trainDetails); 
//	  }
//	 
	@Test
	public void deleteTrainDetailsTest() {
		TrainDetails trainDetails = new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL",
				"21:05", "15:00", "6hr 5mins", 12, 323, 456, 433, 556);
		adminRepository.delete(trainDetails);
		verify(adminRepository,times(1)).delete(trainDetails);
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
	       when(adminRepository.findById(anyInt())).thenReturn(Optional.of(f));
	       TrainDetails existingTrains = adminService.getDetailsByTrainNo(12640);
		
		   assertNotNull(existingTrains);
		   assertThat(existingTrains.getTrainNo()).isEqualTo(12640);
	}
	  @Test
	    public void updateTrain () {
		  TrainDetails u = new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL",
					"21:05", "15:00", "6hr 5mins", 12, 323, 456, 433, 556);
	    when(adminRepository.findById(anyInt())).thenReturn(Optional.of(u));
	    when(adminRepository.save(any(TrainDetails.class))).thenReturn(u);
		u.setArrivalTime("21:35");
		
		TrainDetails updatetrain = adminService.updateTrainDetails(12640, u);
		
		assertNotNull(updatetrain);
		assertEquals("21:35", updatetrain.getArrivalTime());
	  }

	    @Test
	    public void deleteTrain() {
	    	 TrainDetails u = new TrainDetails(12640, "BRINDAVAN EXPRESS", "KSRBENGALURU", "MGRCHENNAICTL",
						"21:05", "15:00", "6hr 5mins", 12, 323, 456, 433, 556);
		
		when(adminRepository.findById(anyInt())).thenReturn(Optional.of(u));
	   doNothing().when(adminRepository).delete(any(TrainDetails.class));
	    
	   adminService.deleteTrainDetails(12640);
	   verify(adminRepository, times(1)).findById(12640);
	   verify(adminRepository, times(1)).deleteById(12640);
	    }
}