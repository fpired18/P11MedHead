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

		if (count == 0) {
			Hospital h1 = new Hospital();

			h1.setSpecialityGroup("Groupe dentaire");
			h1.setSpeciality("Orthodontie");
			h1.setHospitalCenter("Brest");
			h1.setNumberOfBeds(10);
			h1.setNumberOfPatients(5);
			h1.setGeographicalPositionLong(5);
			h1.setGeographicalPositionLat(7);
			h1.getNumberOfBedsAvailable();

			Hospital h2 = new Hospital();

			h2.setSpecialityGroup("Anesthésie");
			h2.setSpeciality("Anesthésie");
			h2.setHospitalCenter("Bordeaux");
			h2.setNumberOfBeds(150);
			h2.setNumberOfPatients(75);
			h2.setGeographicalPositionLong(6);
			h2.setGeographicalPositionLat(8);
			h2.getNumberOfBedsAvailable();

			Hospital h3 = new Hospital();

			h3.setSpecialityGroup("Groupe de médecine générale");
			h3.setSpeciality("Allergie");
			h3.setHospitalCenter("Toulouse");
			h3.setNumberOfBeds(70);
			h3.setNumberOfPatients(10);
			h3.setGeographicalPositionLong(4);
			h3.setGeographicalPositionLat(2);
			h3.getNumberOfBedsAvailable();

			Hospital h4 = new Hospital();

			h4.setSpecialityGroup("Groupe pathologie");
			h4.setSpeciality("Pédiatrie");
			h4.setHospitalCenter("Valence");
			h4.setNumberOfBeds(80);
			h4.setNumberOfPatients(40);
			h4.setGeographicalPositionLong(6);
			h4.setGeographicalPositionLat(9);
			h4.getNumberOfBedsAvailable();

			Hospital h5 = new Hospital();

			h5.setSpecialityGroup("Groupe de psychiatrie");
			h5.setSpeciality("Psychiatrie légale");
			h5.setHospitalCenter("Lille");
			h5.setNumberOfBeds(30);
			h5.setNumberOfPatients(7);
			h5.setGeographicalPositionLong(9);
			h5.setGeographicalPositionLat(10);
			h5.getNumberOfBedsAvailable();

			hospitalDAO.save(h1);
			hospitalDAO.save(h2);
			hospitalDAO.save(h3);
			hospitalDAO.save(h4);
			hospitalDAO.save(h5);
		}

	}

}
