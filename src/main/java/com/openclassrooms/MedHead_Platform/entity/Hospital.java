package com.openclassrooms.MedHead_Platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	public Long id;

	public String city;

	public String name;
	
	public int beds;

	public int bedsa;

	public double lat;

	public double lon;
	
	@JsonInclude()
	@Transient
	public double distance;


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public Hospital() {
	}


	public Hospital(Long id, String city, String name, int beds, int bedsa, double lat, double lon) {
		super();
		this.id = id;
		this.city = city;
		this.name = name;
		this.beds = beds;
		this.bedsa = bedsa;
		this.lat = lat;
		this.lon = lon;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHospitalCenter() {
		return city;
	}

	public void setHospitalCenter(String hospitalCenter) {
		this.city = hospitalCenter;
	}

	public int getNumberOfBeds() {
		return beds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.beds = numberOfBeds;
	}


	public void setNumberOfBedsAvailable(int numberOfBedsAvailable) {
		this.bedsa = numberOfBedsAvailable;
	}

	public double getGeographicalPositionLat() {
		return lat;
	}

	public void setGeographicalPositionLat(double geographicalPositionLat) {
		this.lat = geographicalPositionLat;
	}

	public double getGeographicalPositionLon() {
		return lon;
	}

	public void setGeographicalPositionLon(double geographicalPositionLon) {
		this.lon = geographicalPositionLon;
	}

}
