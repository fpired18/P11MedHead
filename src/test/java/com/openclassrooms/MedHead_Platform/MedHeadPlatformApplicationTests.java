package com.openclassrooms.MedHead_Platform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class MedHeadPlatformApplicationTests {

	@Autowired
	private HospitalDAO hospitalDAO;
	
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	public MockMvc mockMvc;
	
	ObjectMapper MAPPER = new ObjectMapper();
	
	@Before(value = "0")
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	@Order(1)
	public void myAGetHospitalTest() throws Exception {
		mockMvc.perform(get("/hospital"))
		.andExpect(status().isOk())
		//.andExpect(jsonPath("$[1].specialityGroup", is("Anesthesie")))
		; 
	}

	Hospital hospital = new Hospital();
	@Test
	@Order(2)
	public void myBCreateTest() {
		hospital.id = (19L);
		hospital.city = "Chateauroux";
		hospital.name = "Centre Hospitalier";
		hospital.beds = 128;
		hospital.bedsa = 66;
		hospital.lat = 46.81;
		hospital.lon= 1.69;
		hospitalDAO.save(hospital);
		assertNotNull(hospitalDAO.findByNameByCity("Chateauroux").get(0));
	}

	@Test
	@Order(3)
	public void myCReadTest() {
		List<Hospital> list = hospitalDAO.findAll();
		assertThat(list).size().isGreaterThan(10);
	}

	@Test
	@Order(4)
	public void myDSingleHospitalTest() {
		Hospital hospital = hospitalDAO.findById(1L).get();
		System.out.println("Voici hospital.city " + hospital.city);
		assertEquals("Bordeaux", hospital.city);
	}

	@Test
	@Order(5)
	public void myEUpdateTest() {
		Hospital hospital = hospitalDAO.findById(1L).get();
		hospital.beds = 130;
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
