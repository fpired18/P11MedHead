package com.openclassrooms.MedHead_Platform;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.openclassrooms.MedHead_Platform.service.HospitalService;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(controllers = )
public class HospitalControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private HospitalService hospitalService;
	//Given
	double latPatient = 47.08;
	double lonPatient = 2.40;
	double latHospital = 48.39;
	double lonHospital = -4.48;
	
	
	@Test
	public void testGetDistanceLonGPS() throws Exception {
		//When
		double response = hospitalService.lonGPS(latPatient, lonPatient, latHospital, lonHospital);
		//Then
		assertEquals(response, 3458386.3104862766);
	}
	
	@Test
	public void testGetDistanceDistanceGPS() throws Exception {
		//When
		double response = hospitalService.distanceGPS(latPatient, lonPatient, latHospital, lonHospital);
		//Then
		assertEquals(response, 53.38101878586233);
	}
	
	@Test
	public void testGetDistanceTravelGPS() throws Exception {
		//When
		double response = hospitalService.travelGPS(latPatient, lonPatient, latHospital, lonHospital);
		//Then
		assertEquals(response, 281.8246465433694);
	}
	
	@Test
	public void testGetDistanceShortTravel() throws Exception {
		//When
		double response = hospitalService.shortTravel(latPatient, lonPatient, latHospital, lonHospital);
		//Then
		assertEquals(response, 321047.016511935);
	}
	
	@Test
	public void testGetRadWithDegre() throws Exception {
		//Given
		double deg = 35;
		//When
		double response = hospitalService.deg2rad(deg);
		System.out.println("Voici le retour de deg2rad:" + response);
		//Then
		assertEquals(response, 0.6108652381980153);
	}
	
	@Test
	public void testGetDegreWithRad() throws Exception {
		//Given
		double rad = 0.6108652381980153;
		//When
		double response = hospitalService.rad2deg(rad);
		System.out.println("Voici le retour de deg2rad:" + response);
		//Then
		assertEquals(response, 35);
	}
	
	
	@Test
	public void testGetHospital() throws Exception {
		ResultActions result = mockMvc.perform(get("/hospital"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is("Hôpital Saint-André")));
		
		System.out.println("**************************************************************** "+ result.toString());
		System.out.println("Voici Result "+ result.toString());
		
	}
	/*
	@Test
	public void testGetHospital2() throws Exception {
		ResultActions result = mockMvc.perform(get("/hospital"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[1].specialityGroup", is("Groupe dentaire")));
		
		System.out.println("Result "+ result.toString());
		
	}*/

}
