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
	
	public List<Hospital> findBySpeciality(String speciality) {
		return hospitalDAO.findBySpeciality(speciality); 
	}
	
	public List<Hospital> findBySpecialityGroup(String specialityGroup) {
		return hospitalDAO.findBySpecialityGroup(specialityGroup);
	}
	
	public List<Hospital> getAllHospitals(){
		List<Hospital> hospital = new ArrayList<Hospital>();
		hospitalDAO.findAll().forEach(hospital1 -> hospital.add(hospital1));
		return hospital;
	}
	
	public Hospital getHospitalById(Long id) {
		return hospitalDAO.findById(id).get();
	}
	
	public void saveOrUpdate(Hospital hospital) {
		hospitalDAO.save(hospital);
	}
	
	public void update(Hospital hospital, Long id) {
		hospitalDAO.save(hospital);
	}
	
}
