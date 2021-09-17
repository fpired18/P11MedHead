package com.openclassrooms.MedHead_Platform.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Repository
public interface HospitalDAO extends CrudRepository<Hospital, Long> {
	
	public List<Hospital> findBySpecialityGroup(String name);
	public List<Hospital> findBySpeciality(String name);
	public List<Hospital> findByHospitalCenter(String name);
	
	public List<Hospital> findByNumberOfBeds(Integer number);
	public List<Hospital> findByNumberOfPatients(Integer number);
	public List<Hospital> findByGeographicalPositionLong(Integer number);
	public List<Hospital> findByGeographicalPositionLat(Integer number);
	public List<Hospital> findByNumberOfBedsAvailable(Integer number);
	public List<Hospital> findAll();

	
public static int distanceGPS (int latPatientDegre, int longPatientDegre, int latHospitalDegre, int longHospitalDegre) {
		
		int radius = 6378000;
		
		int latPatient = (int) Math.toRadians(latPatientDegre);
		int longPatient = (int) Math.toRadians(longPatientDegre);
		
		int latHospital = (int) Math.toRadians(latHospitalDegre);
		int longHospital = (int) Math.toRadians(longHospitalDegre);
		
		int d = (int) (radius * (Math.PI/2 - Math.asin(Math.sin(latHospital) * Math.sin(latPatient) + Math.cos(longHospital-longPatient) * Math.cos(latHospital)* Math.cos(latPatient))));
		return d;
	}

	

}
