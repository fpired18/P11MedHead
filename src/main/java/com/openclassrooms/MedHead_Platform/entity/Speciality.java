package com.openclassrooms.MedHead_Platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality { // extends Hospital
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long specialityId;

	public String groups;
	public String speciality;

	public Speciality() {

	}

	public Speciality(Long specialityId, String groups, String speciality) {
		super();
		this.specialityId = specialityId;
		this.groups = groups;
		this.speciality = speciality;
	}

	public Long getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Long specialityId) {
		this.specialityId = specialityId;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "Speciality2 [specialityId=" + specialityId + ", groups=" + groups + ", speciality=" + speciality + "]";
	}
	

}
