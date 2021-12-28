package com.openclassrooms.MedHead_Platform.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Repository
public interface HospitalDAO extends CrudRepository<Hospital, Long> {


	public List<Hospital> findAll();
	
	
	@Query(value = "select h.* from hospital h join hospitalspeciality hs on (h.id = hs.hospitalid) join specialities s on (s.id=hs.specialityid) where s.speciality=?1 ", nativeQuery = true)
	public List<Hospital> findByBySpeciality(String speciality);
	
	@Query(value = "select city from hospital h join hospitalspeciality hs on (h.id = hs.hospitalid) join specialities s on (s.id=hs.specialityid) where s.speciality=?1 ", nativeQuery = true)
	 List<String> findByCityBySpecialities(String speciality);
	
	@Query(value = "select speciality from specialities s join hospitalspeciality hs on (s.id=hs.specialityid) join hospital h on (h.id = hs.hospitalid) where h.city=?1 ", nativeQuery = true)
	 List<String> findBySpecialitiesByCity(String city);
	
	@Query(value = "select groups from specialities s join hospitalspeciality hs on (s.id=hs.specialityid) join hospital h on (h.id = hs.hospitalid) where h.city=?1 ", nativeQuery = true)
	 List<String> findByGroupsByCity(String city);
	
	@Query(value = "select name from hospital where hospital.city=?1 ", nativeQuery = true)
	List<String> findByNameByCity(String city);
	
	@Query(value = "select bedsa from hospital where hospital.city=?1 ", nativeQuery = true)
	List<Integer> findByBedsaByCity(String city);
	
	@Query(value = "select beds from hospital where hospital.city=?1 ", nativeQuery = true)
	List<Integer> findByBedsByCity(String city);
	
	@Query(value = "select lon from hospital where hospital.city=?1 ", nativeQuery = true)
	List<Double> findByLonByCity(String city);
	
	@Query(value = "select lat from hospital where hospital.city=?1 ", nativeQuery = true)
	List<Double> findByLatByCity(String city);
	
	
	@Query(value = "select * from hospital where hospital.id=?1 ", nativeQuery = true)
	List<Hospital> findByHospitalById(Long id);
	
	
	// première méthode de calcul de distances entre deux points GPS_____________________________________________ 
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
	
	// deuxième méthode de calcul de distances entre deux points GPS_________________________________________________
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
	
	// troisième méthode de calcul de distances entre deux points GPS _____________________________________________
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
	
	// quatrième méthode de calcul de distances entre deux points GPS ________________________________________________
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
