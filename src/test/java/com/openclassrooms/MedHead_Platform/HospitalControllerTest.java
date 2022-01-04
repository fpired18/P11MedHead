package com.openclassrooms.MedHead_Platform;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.openclassrooms.MedHead_Platform.entity.Hospital;
import com.openclassrooms.MedHead_Platform.service.HospitalService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class HospitalControllerTest {


	Hospital hospital = new Hospital();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private HospitalService hospitalService;
	// Given
	double latPatient = 47.08;
	double lonPatient = 2.40;
	double latHospital = 48.39;
	double lonHospital = -4.48;

	@Test
	@Order(1)
	public void testGetDistanceLonGPS() throws Exception {
		// When
		double response = hospitalService.lonGPS(latPatient, lonPatient, latHospital, lonHospital);
		// Then
		assertEquals(response, 3458386.3104862766);
	}

	@Test
	// @Order(2)
	public void testGetDistanceDistanceGPS() throws Exception {
		// When
		double response = hospitalService.distanceGPS(latPatient, lonPatient, latHospital, lonHospital);
		// Then
		assertEquals(response, 53.38101878586233);
	}

	@Test
	@Order(3)
	public void testGetDistanceTravelGPS() throws Exception {
		// When
		double response = hospitalService.travelGPS(latPatient, lonPatient, latHospital, lonHospital);
		// Then
		assertEquals(response, 281.8246465433694);
	}

	@Test
	@Order(4)
	public void testGetDistanceShortTravel() throws Exception {
		// When
		double response = hospitalService.shortTravel(latPatient, lonPatient, latHospital, lonHospital);
		// Then
		assertEquals(response, 321047.016511935);
	}

	@Test
	@Order(5)
	public void testGetRadWithDegre() throws Exception {
		// Given
		double deg = 35;
		// When
		double response = hospitalService.deg2rad(deg);
		System.out.println("Voici le retour de deg2rad:" + response);
		// Then
		assertEquals(response, 0.6108652381980153);
	}

	@Test
	@Order(6)
	public void testGetDegreWithRad() throws Exception {
		// Given
		double rad = 0.6108652381980153;
		// When
		double response = hospitalService.rad2deg(rad);
		System.out.println("Voici le retour de deg2rad:" + response);
		// Then
		assertEquals(response, 35);
	}

	@Test
	@Order(7)
	public void testGetHospital() throws Exception {
		ResultActions result = mockMvc.perform(get("/hospital")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[5].name", is("CHU"))); // Hôpital de Hautepierre

		System.out.println("**************************************************************** " + result.toString());
		System.out.println("Voici Result " + result.toString());

	}

	@Test
	@Order(8)
	public void testGetHospital1() throws Exception {
		mockMvc.perform(get("/hospital")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[1].city", is("Mulhouse")));// Brest
	}

	@Test
	@Order(9)
	public void testGetHospital2() throws Exception {
		mockMvc.perform(get("/hospital")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].bedsa", is(75)));
	}

	@Test
	@Order(10)
	public void testGetHospital3() throws Exception {
		mockMvc.perform(get("/hospital")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[8].beds", is(200)));// 80
	}

	@Test
	@Order(11)
	public void testGetHospital4() throws Exception {
		mockMvc.perform(get("/hospital")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[7].lat", is(47.23)));// 47.9
	}

	@Test
	@Order(12)
	public void testGetHospital5() throws Exception {
		ResultActions result = mockMvc.perform(get("/hospital")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[2].lon", is(-4.48)));// -1.62
		System.out.println("******************************************************************** ");
		System.out.println("Result " + result.toString());
	}

	@Test
	public void testSpecialitycity() throws Exception {
		mockMvc.perform(get("/hospital/specialitycity?city=Brest")).andDo(print())
				.andExpect(jsonPath("$[0]", is("CARDIOLOGIE"))).andExpect(status().isOk());
	}

	@Test
	public void testCityBySpeciality() throws Exception {
		mockMvc.perform(get("/hospital/cityspeciality?speciality=CARDIOLOGIE")).andDo(print())
				.andExpect(jsonPath("$[0]", is("Bordeaux"))).andExpect(status().isOk());
	}

	@Test
	public void testGroupsByCity() throws Exception {
		mockMvc.perform(get("/hospital/groupscity?city=Nantes")).andDo(print())
				.andExpect(jsonPath("$[0]", is("MEDECINE GENERALE"))).andExpect(status().isOk());
	}

	@Test
	public void testNameByCity() throws Exception {
		mockMvc.perform(get("/hospital/namecity?city=Mulhouse")).andDo(print())
				.andExpect(jsonPath("$[0]", is("Centre Hospitalier"))).andExpect(status().isOk());
	}

	@Test
	public void testBedsaByCity() throws Exception {
		mockMvc.perform(get("/hospital/bedsacity?city=Orléans")).andDo(print()).andExpect(jsonPath("$[0]", is(20)))
				.andExpect(status().isOk());
	}

	@Test
	public void testBedsByCity() throws Exception {
		mockMvc.perform(get("/hospital/bedscity?city=Toulouse")).andDo(print()).andExpect(jsonPath("$[0]", is(70)))
				.andExpect(status().isOk());
	}

	@Test
	public void testLonByCity() throws Exception {
		mockMvc.perform(get("/hospital/loncity?city=Lille")).andDo(print()).andExpect(jsonPath("$[0]", is(3.08)))
				.andExpect(status().isOk());
	}

	@Test
	public void testLatByCity() throws Exception {
		mockMvc.perform(get("/hospital/latcity?city=Mulhouse")).andDo(print()).andExpect(jsonPath("$[0]", is(47.74)))
				.andExpect(status().isOk());
	}

	@Test
	public void testHospitalByid() throws Exception {
		mockMvc.perform(get("/hospital/idid?id=2")).andDo(print())
				.andExpect(jsonPath("$[0].name", is("Centre Hospitalier"))).andExpect(status().isOk());
	}

	@Test
	public void testAllHospitalsBySpeciality() throws Exception {
		mockMvc.perform(get("/hospital2?speciality=CARDIOLOGIE")).andDo(print())
				.andExpect(jsonPath("$[0].city", is("Bordeaux"))).andExpect(status().isOk());
	}

	@Test
	public void testAllHospitalsBySpeciality2() throws Exception {
		mockMvc.perform(get("/hospital2?speciality=cardiologie")).andDo(print()).andExpect(status().isNoContent());
	}

	@Test
	public void testAllHospitalsBySpeciality3() throws Exception {
		mockMvc.perform(get("/hospital2?speciality=")).andDo(print()).andExpect(status().isNoContent());
	}

	@Test
	public void testSaveHospital() throws Exception {
		hospital.beds = 20;
		hospital.bedsa = 5;
		hospital.city = "Paris";
		hospital.name = "Hopital Bichat";
		hospital.id = (long) 20;
		hospital.lat = 48.89;
		hospital.lon = 2.34;

		// mockMvc.perform(post("/hospital")).andExpect(status().isOk());
		// hospitalService.save(hospital);
	}

	/*
	 * @Test public void testAllHospitalNumberOfBedsSpeciality() throws Exception {
	 * HospitalWithDisponibilityRequest request = new
	 * HospitalWithDisponibilityRequest();
	 * request.setSpecialityRequest("CARDIOLOGIE"); request.setLatPatient(4);
	 * request.setLonPatient(1); mockMvc.perform(get("/hospital/speciality"))
	 * .andExpect(status().isOk())
	 * .andExpect(content().json(request)).andDo(print())
	 * .andExpect(jsonPath("$[0].city", is("Bordeaux")))); }
	 */

}
