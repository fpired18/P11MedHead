package com.openclassrooms.MedHead_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Service
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	HospitalDAO hospitalDAO;


	@Override
	public List<Hospital> findAll() {
		return hospitalDAO.findAll();
	}

	@Override
	public Optional<Hospital> findById(Long id) {
		return hospitalDAO.findById(id);
	}

	@Override
	public List<String> findByCityBySpecialities(String speciality) {
		return hospitalDAO.findByCityBySpecialities(speciality);
	}

	@Override
	public List<String> findBySpecialitiesByCity(String city) {
		return hospitalDAO.findBySpecialitiesByCity(city);
	}

	@Override
	public List<String> findByGroupsByCity(String city) {
		return hospitalDAO.findByGroupsByCity(city);
	}

	@Override
	public List<String> findByNameByCity(String city) {
		return hospitalDAO.findByNameByCity(city);
	}

	@Override
	public List<Integer> findByBedsaByCity(String city) {
		return hospitalDAO.findByBedsaByCity(city);
	}

	@Override
	public List<Integer> findByBedsByCity(String city) {
		return hospitalDAO.findByBedsByCity(city);
	}
	
	@Override
	public List<Double> findByLonByCity(String city) {
		return hospitalDAO.findByLonByCity(city);
	}
	
	@Override
	public List<Double> findByLatByCity(String city) {
		return hospitalDAO.findByLatByCity(city);
	}
	
	//***************Nouveau************************
	
	@Override
	public List<Hospital> findByHospitalById(Long id) {
		return hospitalDAO.findByHospitalById(id);
	}
	

	@Override
	public Hospital save(Hospital hospital) {
		return hospitalDAO.save(hospital);
	}

	@Override
	public List<Hospital> findByBySpeciality(String speciality) {
		
		return hospitalDAO.findByBySpeciality(speciality);
	}
	
	
	//***************Nouveau


	public double lonGPS (double latPatient, double lonPatient, double latHospital, double lonHospital) {
		double pk = (180/Math.PI);
		
		double latP = latPatient / pk;
		double lonP = lonPatient / pk;
		double latH = latHospital / pk;
		double lonH = lonHospital / pk;
		
		double dist1 = Math.cos(latP) * Math.cos(lonP) * Math.cos(latH) * Math.cos(lonH);
		double dist2 = Math.cos(latP) * Math.sin(lonP) * Math.cos(latH) * Math.sin(lonH);
		double dist3 = Math.sin(latP) * Math.sin(latH);
		
		double distTotal = Math.cos(dist1 + dist2 + dist3);
		
		return 6366000 * distTotal;
	}
	
	// deuxi??me m??thode de calcul de distances entre deux points GPS_________________________________________
	public double distanceGPS(double latPatient, double lonPatient, double latHospital, double lonHospital) {
		double theta = lonPatient - lonHospital;
		double dist = Math.sin(deg2rad(latPatient)) * Math.sin(deg2rad(latHospital))
				+ Math.cos(deg2rad(latPatient)) * Math.cos(deg2rad(latHospital)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.15 * 1.609344;
		return (dist/10);
	}

	public double deg2rad(double deg) {
		return (deg * Math.PI) / 180.0;
	}

	public double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
	
	// troisi??me m??thode de calcul de distances entre deux points GPS _____________________________________
	public double travelGPS(double latPatient, double lonPatient, double latHospital, double lonHospital) {
		double d2r = (180 / Math.PI);
		double distance = 0;

		try{
		    double dlong = (lonHospital - lonPatient) * d2r;
		    double dlat = (latHospital - latPatient) * d2r;
		    double a =
		        Math.pow(Math.sin(dlat / 2.0), 2)
		            + Math.cos(latPatient * d2r)
		            * Math.cos(latHospital * d2r)
		            * Math.pow(Math.sin(dlong / 2.0), 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    distance = (6367 * c)/ 10;

		    return distance;

		} catch(Exception e){
		    e.printStackTrace();
		}
		return distance;
	}
	// quatri??me m??thode de calcul de distances entre deux points GPS ________________________________________
	public double shortTravel (double latPatient, double lonPatient, double latHospital, double lonHospital) {
		int rayon = 637800;
		double latP = deg2rad(latPatient);
		double lonP = deg2rad(lonPatient);
		double latH = deg2rad(latHospital);
		double lonH = deg2rad(lonHospital);
		
		double d = rayon * (Math.PI/2 - Math.asin(Math.sin(latH * Math.sin(latP) + Math.cos(lonH-lonP) * Math.cos(latH) * Math.cos(latP))));
		return d;
		
	}



}
