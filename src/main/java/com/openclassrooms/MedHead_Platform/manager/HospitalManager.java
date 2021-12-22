package com.openclassrooms.MedHead_Platform.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital3;

@Component
public class HospitalManager {
 
	@Autowired
	HospitalDAO hospitalDAO;
	
	/*public List<Hospital3> findBySpecialityGroup(String specialityGroup) {
		return hospitalDAO.findBySpecialityGroup(specialityGroup);
	}
	
	public List<Hospital3> findBySpeciality(String speciality) {
		return hospitalDAO.findBySpeciality(speciality); 
	}
	
	public List<Hospital3> findByHospitalCenter(String hospitalCenter) {
		return hospitalDAO.findByHospitalCenter(hospitalCenter);
	}
	
	public List<Hospital3> findByNumberOfBeds(Integer numberOfBeds) {
		return hospitalDAO.findByNumberOfBeds(numberOfBeds);
	}
	
	public List<Hospital3> findByNumberOfPatients(Integer numberOfPatients) {
		return hospitalDAO.findByNumberOfPatients(numberOfPatients);
	}
	
	public List<Hospital3> findByGeographicalPositionLat(Double geographicalPositionLat) {
		return hospitalDAO.findByGeographicalPositionLat(geographicalPositionLat);
	}
	
	public List<Hospital3> findByGeographicalPositionLon(Double geographicalPositionLon) {
		return hospitalDAO.findByGeographicalPositionLon(geographicalPositionLon);
	}
	
	public List<Hospital3> getAllHospitals(){
		List<Hospital3> hospital = new ArrayList<Hospital3>();
		hospitalDAO.findAll().forEach(hospital1 -> hospital.add(hospital1));
		return hospital;
	}
	
	public Hospital3 getHospitalById(Long id) {
		return hospitalDAO.findById(id).get();
	}
	
	public void saveOrUpdate(Hospital3 hospital) {
		hospitalDAO.save(hospital);
	}
	
	public void update(Hospital3 hospital, Long id) {
		hospitalDAO.save(hospital);
	}*/
	
}
