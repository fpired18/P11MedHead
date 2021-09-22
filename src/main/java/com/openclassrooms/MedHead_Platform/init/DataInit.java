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
			Hospital h1 = new Hospital("Groupe dentaire", "Orthodontie", "Brest", 10, 5, 48.39, -4.48);
			Hospital h2 = new Hospital("Anesthésie", "Anesthésie", "Bordeaux", 150, 75, 44.83, -0.60);
			Hospital h3 = new Hospital("Groupe de médecine générale", "Allergie", "Toulouse", 70, 10, 43.61, 1.39);
			Hospital h4 = new Hospital("Groupe pathologie", "Pédiatrie", "Lille", 80, 40, 50.62, 3.08);
			Hospital h5 = new Hospital("Groupe de psychiatrie", "Psychiatrie légale", "Marseille", 30, 7, 43.30, 5.38);
			Hospital h6 = new Hospital("Groupe chirurgical", "Neurochirugie", "Lyon", 80, 55, 45.77, 4.85);

			hospitalDAO.save(h1);
			hospitalDAO.save(h2);
			hospitalDAO.save(h3);
			hospitalDAO.save(h4);
			hospitalDAO.save(h5);
			hospitalDAO.save(h6);
		}

	}

}
