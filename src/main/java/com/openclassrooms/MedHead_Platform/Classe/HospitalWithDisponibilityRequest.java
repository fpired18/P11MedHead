package com.openclassrooms.MedHead_Platform.Classe;

public class HospitalWithDisponibilityRequest {
	
	private  String specialityRequest;
	private int numberOfBedsAvailableRequest;
	private double latPatient;
	private double lonPatient ;	
	
	public String getSpecialityRequest() {
		return specialityRequest;
	}
	public void setSpecialityRequest(String specialityRequest) {
		this.specialityRequest = specialityRequest;
	}
	public int getNumberOfBedsAvailableRequest() {
		return numberOfBedsAvailableRequest;
	}
	public void setNumberOfBedsAvailableRequest(int numberOfBedsAvailableRequest) {
		this.numberOfBedsAvailableRequest = numberOfBedsAvailableRequest;
	}
	public double getLatPatient() {
		return latPatient;
	}
	public void setLatPatient(double latPatient) {
		this.latPatient = latPatient;
	}
	public double getLonPatient() {
		return lonPatient;
	}
	public void setLonPatient(double lonPatient) {
		this.lonPatient = lonPatient;
	}
	
}
