package com.openclassrooms.testsMedHead_Platform;

import java.util.ArrayList;

class NewsDTO {
	String status;
	int totalResults;
	ArrayList<HospitalDTO> articles;

}

public class HospitalDTO {
	
	SourceDTO source;

	String specialityGroup;
	String speciality;
	String hospitalCenter;
	int numberOfBeds;
	int numberOfPatients;
	int numberOfBedsAvailable;
	int geographicalPositionLong;
	int geographicalPositionLat;

}

class SourceDTO {
	String id;
}
