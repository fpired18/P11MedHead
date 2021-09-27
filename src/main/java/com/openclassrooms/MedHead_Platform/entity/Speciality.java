package com.openclassrooms.MedHead_Platform.entity;

public class Speciality {
	
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
	
	public Speciality(Boolean anesthesy, Boolean allergy, Boolean dermatology, Boolean endodonty, Boolean parodonty,
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

}
