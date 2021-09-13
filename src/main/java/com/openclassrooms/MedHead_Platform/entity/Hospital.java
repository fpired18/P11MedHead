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

	@Column(name = "specialtyGroup", nullable = false)
	public String specialtyGroup;
	@Column(name = "specialty", nullable = false)
	public String specialty;
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

	public String getSpecialtyGroup() {
		return specialtyGroup;
	}

	public void setSpecialtyGroup(String specialtyGroup) {
		this.specialtyGroup = specialtyGroup;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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
