package com.openclassrooms.MedHead_Platform.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Component
public class DataInit implements ApplicationRunner {
	private HospitalDAO hospitalDAO;
	

	public DataInit(HospitalDAO hospitalDAO) {
		this.hospitalDAO = hospitalDAO;
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = hospitalDAO.count();
		
		if(count == 0){
			Hospital h1 = new Hospital();
			
			h1.setSpecialtyGroup("Groupe dentaire");
			h1.setSpecialty("Orthodontie");
			h1.setHospitalCenter("Brest");
			h1.setNumberOfBeds(10);
			h1.setNumberOfPatients(5);
			h1.setPositionGeographique(5);
			
			hospitalDAO.save(h1);
			
			
		}
		
	}

}
