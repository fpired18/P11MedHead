package com.openclassrooms.MedHead_Platform.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Repository
public interface HospitalDAO extends CrudRepository<Hospital, Long> {
	
	public List<Hospital> findBySpecialtyGroup(String name);
	public List<Hospital> findBySpecialty(String name);
	public List<Hospital> findByHospitalCenter(String name);
	
	public List<Hospital> findByNumberOfBeds(Integer number);
	public List<Hospital> findByNumberOfPatients(Integer number);
	public List<Hospital> findByPositionGeographique(Integer number);
	

}
