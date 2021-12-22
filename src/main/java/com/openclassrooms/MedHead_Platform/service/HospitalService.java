package com.openclassrooms.MedHead_Platform.service;

import java.util.List;
import java.util.Optional;

import com.openclassrooms.MedHead_Platform.entity.Hospital;
import com.openclassrooms.MedHead_Platform.entity.Hospital3;


public interface HospitalService {

	/*public List<Hospital3> findBySpecialityGroup(String name);

	public List<Hospital3> findBySpeciality(String name);

	public List<Hospital3> findByHospitalCenter(String name);

	public List<Hospital3> findByNumberOfBeds(Integer number);

	public List<Hospital3> findByNumberOfPatients(Integer number);

	public List<Hospital3> findByNumberOfBedsAvailable(Integer number);

	public List<Hospital3> findByGeographicalPositionLat(Double number);

	public List<Hospital3> findByGeographicalPositionLon(Double number);*/

	public List<Hospital> findAll();

	//public List<Hospital3> findByNumberOfBedsAvailableGreaterThan(Integer number);

	List<String> findByCityBySpecialities(String speciality);

	List<String> findBySpecialitiesByCity(String city);

	List<String> findByGroupsByCity(String city);

	List<String> findByNameByCity(String city);

	List<Integer> findByBedsaByCity(String city);

	List<Integer> findByBedsByCity(String city);
	
	List<Double> findByLonByCity(String city);
	
	List<Double> findByLatByCity(String city);
	
	
	//********************************************

	public List<Hospital> findByHospitalById(Long id);
	
	//**********************************************
	//List<Hospital3> findByCityAndBedsaByCity();

	// première méthode de calcul de distances entre deux points
	// GPS_________________________________________________
	public double lonGPS(double latPatient, double lonPatient, double latHospital, double lonHospital);

	// deuxième méthode de calcul de distances entre deux points
	// GPS_____________________________________________________
	public double distanceGPS(double latPatient, double lonPatient, double latHospital, double lonHospital);

	public double deg2rad(double deg);

	public double rad2deg(double rad);

	// troisième méthode de calcul de distances entre deux points GPS
	// ___________________________________________________
	public double travelGPS(double latPatient, double lonPatient, double latHospital, double lonHospital);

	// quatrième méthode de calcul de distances entre deux points GPS
	// ___________________________________________________
	public double shortTravel(double latPatient, double lonPatient, double latHospital, double lonHospital);

	//public Hospital3 save(Hospital3 hospital);

	public Optional<Hospital> findById(Long id);

	

	

}
