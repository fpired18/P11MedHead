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
			Hospital h1 = new Hospital("groupe dentaire", "orthodontie", "brest", 10, 5, 48.39, -4.48);
			Hospital h2 = new Hospital("anesthesie", "anesthesie", "bordeaux", 150, 75, 44.83, -0.60);
			Hospital h3 = new Hospital("groupe de medecine generale", "allergie", "toulouse", 70, 10, 43.61, 1.39);
			Hospital h4 = new Hospital("groupe pathologie", "cardiologie", "lille", 80, 40, 50.62, 3.08);
			Hospital h5 = new Hospital("groupe de psychiatrie", "psychiatrie legale", "marseille", 30, 7, 43.30, 5.38);
			Hospital h6 = new Hospital("groupe chirurgical", "neurochirugie", "lyon", 80, 55, 45.77, 4.85);
			Hospital h7 = new Hospital("groupe de medecine generale", "pediatrie", "orleans", 200, 180, 47.90, 1.89);
			Hospital h8 = new Hospital("groupe de medecine generale", "cardiologie", "strasbourg", 200, 199, 48.58, 7.74);

			hospitalDAO.save(h1);
			hospitalDAO.save(h2);
			hospitalDAO.save(h3);
			hospitalDAO.save(h4);
			hospitalDAO.save(h5);
			hospitalDAO.save(h6);
			hospitalDAO.save(h7);
			hospitalDAO.save(h8);
		}

	}

}
