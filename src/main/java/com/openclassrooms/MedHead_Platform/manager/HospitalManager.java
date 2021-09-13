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
	
	public List<Hospital> findBySpecialty(String specialty) {
		return hospitalDAO.findBySpecialty(specialty); 
	}
	
	public List<Hospital> findBySpecialtyGroup(String specialtyGroup) {
		return hospitalDAO.findBySpecialtyGroup(specialtyGroup);
	}
}
