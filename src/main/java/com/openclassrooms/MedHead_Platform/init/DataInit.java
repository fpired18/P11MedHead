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
			
			Hospital h2 = new Hospital();
			
			h2.setSpecialtyGroup("Anesthésie");
			h2.setSpecialty("Anesthésie");
			h2.setHospitalCenter("Bordeaux");
			h2.setNumberOfBeds(150);
			h2.setNumberOfPatients(75);
			h2.setPositionGeographique(15);
			
			Hospital h3 = new Hospital();
			
			h3.setSpecialtyGroup("Groupe de médecine générale");
			h3.setSpecialty("Allergie");
			h3.setHospitalCenter("Toulouse");
			h3.setNumberOfBeds(70);
			h3.setNumberOfPatients(10);
			h3.setPositionGeographique(20);
			
			Hospital h4 = new Hospital();
			
			h4.setSpecialtyGroup("Groupe pathologie");
			h4.setSpecialty("Pédiatrie");
			h4.setHospitalCenter("Valence");
			h4.setNumberOfBeds(80);
			h4.setNumberOfPatients(40);
			h4.setPositionGeographique(15);
			
			hospitalDAO.save(h1);
			hospitalDAO.save(h2);
			hospitalDAO.save(h3);
			hospitalDAO.save(h4);
		}
		
	}

}
