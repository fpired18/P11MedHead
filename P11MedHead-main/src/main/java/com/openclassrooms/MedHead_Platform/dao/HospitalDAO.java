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

	public List<Hospital> findByNumberOfBedsAvailable(Integer number);
	
	public List<Hospital> findByGeographicalPositionLat(Double number);

	public List<Hospital> findByGeographicalPositionLon(Double number);

	public List<Hospital> findAll();

	// public List<Hospital> findByNumberOfBedsAvailableBetween(Integer number1,
	// Integer number2);
	public List<Hospital> findByNumberOfBedsAvailableGreaterThan(Integer number);
	
	// première méthode de calcul de distances entre deux points GPS_________________________________________________ 
	public default double lonGPS (double latPatient, double lonPatient, double latHospital, double lonHospital) {
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
	
	// deuxième méthode de calcul de distances entre deux points GPS_____________________________________________________
	public default double distanceGPS(double latPatient, double lonPatient, double latHospital, double lonHospital) {
		double theta = lonPatient - lonHospital;
		double dist = Math.sin(deg2rad(latPatient)) * Math.sin(deg2rad(latHospital))
				+ Math.cos(deg2rad(latPatient)) * Math.cos(deg2rad(latHospital)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.15 * 1.609344;
		return (dist/10);
	}

	public default double deg2rad(double deg) {
		return (deg * Math.PI) / 180.0;
	}

	public default double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
	
	// troisième méthode de calcul de distances entre deux points GPS ___________________________________________________
	public default double travelGPS(double latPatient, double lonPatient, double latHospital, double lonHospital) {
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
	// quatrième méthode de calcul de distances entre deux points GPS ___________________________________________________
	public default double shortTravel (double latPatient, double lonPatient, double latHospital, double lonHospital) {
		int rayon = 637800;
		double latP = deg2rad(latPatient);
		double lonP = deg2rad(lonPatient);
		double latH = deg2rad(latHospital);
		double lonH = deg2rad(lonHospital);
		
		double d = rayon * (Math.PI/2 - Math.asin(Math.sin(latH * Math.sin(latP) + Math.cos(lonH-lonP) * Math.cos(latH) * Math.cos(latP))));
		return d;
		
	}

}
