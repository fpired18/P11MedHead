package com.openclassrooms.MedHead_Platform.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Component
public class HospitalManager {
 
	@Autowired
	HospitalDAO hospitalDAO;
	
	public List<Hospital> findBySpeciality(String speciality) {
		return hospitalDAO.findBySpeciality(speciality); 
	}
	
	public List<Hospital> findBySpecialityGroup(String specialityGroup) {
		return hospitalDAO.findBySpecialityGroup(specialityGroup);
	}
}
