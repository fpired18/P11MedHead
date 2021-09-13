package com.openclassrooms.MedHead_Platform.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
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
	@Column(name = "hospitalCenter", nullable = false)
	public String hospitalCenter;

	@Column(name = "numberOfBeds", nullable = false)
	public int numberOfBeds;
	@Column(name = "numberOfPatients", nullable = false)
	public int numberOfPatients;
	@Column(name = "geographicalPosition", nullable = false)
	public int geographicalPosition;

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

	public int getGeographicalPosition() {
		return geographicalPosition;
	}

	public void setGeographicalPosition(int geographicalPosition) {
		this.geographicalPosition = geographicalPosition;
	}

}
