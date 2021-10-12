package com.openclassrooms.MedHead_Platform.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
@Inheritance(strategy = InheritanceType.JOINED)
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id", nullable = false)
	public Long id;

	@Column(name = "specialityGroup", nullable = false)
	public String specialityGroup;
	
	@Column(name = "speciality", nullable = false)
	public String speciality;
	//public Speciality speciality = new Speciality(false, false, false);
	
	@Column(name = "hospitalCenter", nullable = false)
	public String hospitalCenter;

	@Column(name = "numberOfBeds", nullable = false)
	public int numberOfBeds;
	
	@Column(name = "numberOfPatients", nullable = false)
	public int numberOfPatients;

	@Column(name = "numberOfBedsAvailable", nullable = false)
	public int numberOfBedsAvailable;

	@Column(name = "geographicalPositionLat", nullable = false)
	public double geographicalPositionLat;
	
	@Column(name = "geographicalPositionLon", nullable = false)
	public double geographicalPositionLon;
	
	@Column(name = "distance", nullable = true)
	public int distance;

	public Hospital() {
	}

	public Hospital(String specialityGroup, String speciality, String hospitalCenter, int numberOfBeds,
			int numberOfPatients, double geographicalPositionLat, double geographicalPositionLon, int distance) {
		super();
		this.specialityGroup = specialityGroup;
		this.speciality = speciality;
		this.hospitalCenter = hospitalCenter;
		this.numberOfBeds = numberOfBeds;
		this.numberOfPatients = numberOfPatients;
		this.numberOfBedsAvailable = numberOfBeds - numberOfPatients;
		this.geographicalPositionLat = geographicalPositionLat;
		this.geographicalPositionLon = geographicalPositionLon;	
		this.distance = distance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecialityGroup() {
		return specialityGroup;
	}

	public void setSpecialityGroup(String specialityGroup) {
		this.specialityGroup = specialityGroup;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHospitalCenter() {
		return hospitalCenter;
	}

	public void setHospitalCenter(String hospitalCenter) {
		this.hospitalCenter = hospitalCenter;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getNumberOfPatients() {
		return numberOfPatients;
	}

	public void setNumberOfPatients(int numberOfPatients) {
		this.numberOfPatients = numberOfPatients;
	}

	public int getNumberOfBedsAvailable() {
		return numberOfBeds - numberOfPatients;
	}

	public void setNumberOfBedsAvailable(int numberOfBedsAvailable) {
		this.numberOfBedsAvailable = numberOfBedsAvailable;
	}

	public double getGeographicalPositionLat() {
		return geographicalPositionLat;
	}

	public void setGeographicalPositionLat(double geographicalPositionLat) {
		this.geographicalPositionLat = geographicalPositionLat;
	}

	public double getGeographicalPositionLon() {
		return geographicalPositionLon;
	}

	public void setGeographicalPositionLon(double geographicalPositionLon) {
		this.geographicalPositionLon = geographicalPositionLon;
	}
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}
