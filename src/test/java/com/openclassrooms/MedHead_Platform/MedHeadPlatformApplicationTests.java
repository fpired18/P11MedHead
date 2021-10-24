package com.openclassrooms.MedHead_Platform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class MedHeadPlatformApplicationTests {

	@Autowired
	private HospitalDAO hospitalDAO;

	@Autowired
	public MockMvc mockMvc;

	@Test
	@Order(1)
	public void myAGetHospitalTest() throws Exception {
		mockMvc.perform(get("/hospital")).andExpect(status().isOk()); // jsonPath("$[2].firstName", is("Bordeaux"))
	}

	@Test
	@Order(2)
	public void myBCreateTest() {
		Hospital hospital = new Hospital();
		hospital.id = (19L);
		hospital.specialityGroup = "Ostéopathie";
		hospital.speciality = "Colonne Vertébrale";
		hospital.hospitalCenter = "Chateauroux";
		hospital.numberOfBeds = 128;
		hospital.numberOfPatients = 66;
		hospital.geographicalPositionLat = 46.81;
		hospital.geographicalPositionLon = 1.69;
		hospitalDAO.save(hospital);
		assertNotNull(hospitalDAO.findByHospitalCenter("Chateauroux").get(0));

	}

	@Test
	@Order(3)
	public void myCReadTest() {
		List<Hospital> list = hospitalDAO.findAll();
		assertThat(list).size().isGreaterThan(17);
	}

	@Test
	@Order(4)
	public void myDSingleHospitalTest() {
		Hospital hospital = hospitalDAO.findById(1L).get();
		assertEquals("Bordeaux", hospital.hospitalCenter);
	}

	@Test
	@Order(5)
	public void myEUpdateTest() {
		Hospital hospital = hospitalDAO.findById(1L).get();
		hospital.numberOfBeds = 130;
		hospitalDAO.save(hospital);
		assertNotEquals(150, hospitalDAO.findById(1L).get().getNumberOfBeds());
	}

	@Test
	@Order(6)
	public void myFDeleteTest() {
		hospitalDAO.deleteById(1L);
		assertThat(hospitalDAO.existsById(1L)).isFalse();
	}

}
