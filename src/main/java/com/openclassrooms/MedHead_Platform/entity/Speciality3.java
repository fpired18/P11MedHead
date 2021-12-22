package com.openclassrooms.MedHead_Platform.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "specialities2")
@PrimaryKeyJoinColumn(name = "id")
public class Speciality3 { // extends Hospital
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id", nullable = false)
	public Long specialityId;
	
	public Boolean allergy;
	public Boolean anesthesy;
	public Boolean dermatology;
	public Boolean endodonty;
	public Boolean parodonty;
	public Boolean pediatrics;
	public Boolean periodontics;	
	public Boolean neurology;
	public Boolean nuclearMedicine;
	public Boolean virology;
	
	public Speciality3 (){
		
	}
	
	public Speciality3(Boolean anesthesy, Boolean allergy, Boolean dermatology, Boolean endodonty, Boolean parodonty,
			Boolean pediatrics, Boolean periodontics, Boolean neurology,Boolean nuclearMedicine , Boolean virology ) {
		super();
		this.anesthesy = anesthesy;
		this.allergy = allergy;
		this.dermatology = dermatology;
		this.endodonty = endodonty;
		this.parodonty = parodonty;
		this.pediatrics = pediatrics;
		this.periodontics = periodontics;
		this.neurology = neurology;
		this.nuclearMedicine = nuclearMedicine;
		this.virology = virology;
		
		
	}

	public Boolean getAllergy() {
		return allergy;
	}

	public void setAllergy(Boolean allergy) {
		this.allergy = allergy;
	}

	public Boolean getAnesthesy() {
		return anesthesy;
	}

	public void setAnesthesy(Boolean anesthesy) {
		this.anesthesy = anesthesy;
	}

	public Boolean getDermatology() {
		return dermatology;
	}

	public void setDermatology(Boolean dermatology) {
		this.dermatology = dermatology;
	}

	public Boolean getEndodonty() {
		return endodonty;
	}

	public void setEndodonty(Boolean endodonty) {
		this.endodonty = endodonty;
	}

	public Boolean getParodonty() {
		return parodonty;
	}

	public void setParodonty(Boolean parodonty) {
		this.parodonty = parodonty;
	}

	public Boolean getPediatrics() {
		return pediatrics;
	}

	public void setPediatrics(Boolean pediatrics) {
		this.pediatrics = pediatrics;
	}

	public Boolean getPeriodontics() {
		return periodontics;
	}

	public void setPeriodontics(Boolean periodontics) {
		this.periodontics = periodontics;
	}

	public Boolean getNeurology() {
		return neurology;
	}

	public void setNeurology(Boolean neurology) {
		this.neurology = neurology;
	}

	public Boolean getNuclearMedicine() {
		return nuclearMedicine;
	}

	public void setNuclearMedicine(Boolean nuclearMedicine) {
		this.nuclearMedicine = nuclearMedicine;
	}

	public Boolean getVirology() {
		return virology;
	}

	public void setVirology(Boolean virology) {
		this.virology = virology;
	}

	@Override
	public String toString() {
		return "Speciality [allergy=" + allergy + ", anesthesy=" + anesthesy + ", dermatology=" + dermatology
				+ ", endodonty=" + endodonty + ", parodonty=" + parodonty + ", pediatrics=" + pediatrics
				+ ", periodontics=" + periodontics + ", neurology=" + neurology + ", nuclearMedicine=" + nuclearMedicine
				+ ", virology=" + virology + "]";
	}
	
	

}
