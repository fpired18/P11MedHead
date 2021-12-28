package com.openclassrooms.MedHead_Platform.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;




@Component
public class HospitalManager {
 
	@Autowired
	HospitalDAO hospitalDAO;

	
	
	public List<Hospital> getAllHospitals(){
		List<Hospital> hospital = new ArrayList<Hospital>();
		hospitalDAO.findAll().forEach(hospital1 -> hospital.add(hospital1));
		return hospital;
	}

	
	
	//*******************************************Test ***********************
	
	public void saveOrUpdate(Hospital hospital) {
		hospitalDAO.save(hospital);
	}
	
	//*******************************************Test ***********************
	
}
