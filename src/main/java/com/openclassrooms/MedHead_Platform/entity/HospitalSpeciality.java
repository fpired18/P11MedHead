package com.openclassrooms.MedHead_Platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospitalspeciality")
public class HospitalSpeciality { // extends Hospital
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long id;

	public Long specialityid;

	public Long hospitalid;
	
	

	public HospitalSpeciality() {

	}



	public HospitalSpeciality(Long id, Long specialityId, Long hospitalId) {
		super();
		this.id = id;
		this.specialityid = specialityId;
		this.hospitalid = hospitalId;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getSpecialityId() {
		return specialityid;
	}



	public void setSpecialityId(Long specialityId) {
		this.specialityid = specialityId;
	}



	public Long getHospitalId() {
		return hospitalid;
	}



	public void setHospitalId(Long hospitalId) {
		this.hospitalid = hospitalId;
	}


}
