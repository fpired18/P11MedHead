package com.openclassrooms.MedHead_Platform.service;

import java.util.List;
import java.util.Optional;

import com.openclassrooms.MedHead_Platform.entity.Hospital;

public interface HospitalService {

	public List<Hospital> findAll();

	public Hospital save(Hospital hospital);

	public Optional<Hospital> findById(Long id);

	List<String> findByCityBySpecialities(String speciality);

	List<String> findBySpecialitiesByCity(String city);

	List<String> findByGroupsByCity(String city);

	List<String> findByNameByCity(String city);

	List<Integer> findByBedsaByCity(String city);

	List<Integer> findByBedsByCity(String city);

	List<Double> findByLonByCity(String city);

	List<Double> findByLatByCity(String city);

	// ********************************************

	public List<Hospital> findByHospitalById(Long id);
	List<Hospital> findByBySpeciality(String speciality);

	// **********************************************

	// première méthode de calcul de distances entre deux points GPS__________________________________
	public double lonGPS(double latPatient, double lonPatient, double latHospital, double lonHospital);

	// deuxième méthode de calcul de distances entre deux points GPS________________________________________
	public double distanceGPS(double latPatient, double lonPatient, double latHospital, double lonHospital);

	public double deg2rad(double deg);

	public double rad2deg(double rad);

	// troisième méthode de calcul de distances entre deux points GPS_____________________________________
	public double travelGPS(double latPatient, double lonPatient, double latHospital, double lonHospital);

	// quatrième méthode de calcul de distances entre deux points GPS_______________________________________
	public double shortTravel(double latPatient, double lonPatient, double latHospital, double lonHospital);

}
