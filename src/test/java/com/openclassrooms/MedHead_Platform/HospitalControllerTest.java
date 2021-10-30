package com.openclassrooms.MedHead_Platform;

import static org.hamcrest.CoreMatchers.is;
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

@SpringBootTest
@AutoConfigureMockMvc
public class HospitalControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetHospital() throws Exception {
		mockMvc.perform(get("/hospital"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[1].specialityGroup", is("Groupe dentaire")));
		
	}
	
	@Test
	public void testGetHospital2() throws Exception {
		ResultActions result = mockMvc.perform(get("/hospital"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[1].specialityGroup", is("Groupe dentaire")));
		
		System.out.println("Result "+ result.toString());
		
	}

}
