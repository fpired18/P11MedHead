package com.openclassrooms.MedHead_Platform.init;

/*import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;*/

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;
//import com.openclassrooms.MedHead_Platform.entity.Speciality;

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

			/*Speciality listSpecialityH1 = new Speciality(true, true, false);
			Hospital h1 = new Hospital("groupe dentaire", listSpecialityH1, "brest", 10, 5, 48.39, -4.48);
			hospitalDAO.save(h1);*/	
			
			Hospital h1 = new Hospital("Anesthesie", "Anesthesie", "Bordeaux", 150, 75, 44.83, -0.60);
			Hospital h2 = new Hospital("Anesthesie", "Soins intensifs", "Mulhouse", 150, 75, 47.74, 7.33);
			Hospital h3 = new Hospital("Groupe dentaire", "Orthodontie", "Brest", 10, 5, 48.39, -4.48);
			Hospital h4 = new Hospital("Groupe dentaire", "Orthodontie", "Cherbourg", 10, 10, 49.63, -1.62);
			Hospital h5 = new Hospital("Médecine d'urgence", "Médecine d'urgence", "Montpellier", 250, 115, 43.63, 3.86);
			Hospital h6 = new Hospital("Groupe de médecine générale", "Allergie", "Toulouse", 70, 10, 43.61, 1.39);	
			Hospital h7 = new Hospital("Groupe de médecine générale", "Cardiologie", "Strasbourg", 200, 199, 48.58, 7.74);
			Hospital h8 = new Hospital("Groupe de médecine générale", "Cardiologie", "Nantes", 150, 150, 47.23, -1.58);
			Hospital h9 = new Hospital("Groupe de médecine générale", "Pédiatrie", "Orléans", 200, 180, 47.90, 1.89);
			Hospital h10 = new Hospital("Groupe pathologie", "Cardiologie pédiatrique", "Lille", 80, 40, 50.62, 3.08);
			Hospital h11 = new Hospital("Groupe pathologie", "Virologie", "Macon", 250, 115, 46.31, 4.84);
			Hospital h12 = new Hospital("Groupe de psychiatrie", "Psychiatrie légale", "Marseille", 30, 7, 43.30, 5.38);
			Hospital h13 = new Hospital("Groupe de psychiatrie", "Psychiatrie générale", "Orange", 30, 7, 44.13, 4.8);
			Hospital h14 = new Hospital("Groupe radiologie", "Médecine nucléaire", "Paris", 500, 450, 48.84, 2.41);
			Hospital h15 = new Hospital("Groupe chirurgical", "Neurochirugie", "Lyon", 80, 80, 45.77, 4.85);
			Hospital h16 = new Hospital("Groupe chirurgical", "Neurochirugie", "Saint Etienne", 80, 55, 45.41, 4.39);
			Hospital h17 = new Hospital("Groupe chirurgical", "Chirurgie plastique", "Colmard", 250, 115, 48.08, 7.34);
			
			

			hospitalDAO.save(h1);
			hospitalDAO.save(h2);
			hospitalDAO.save(h3);
			hospitalDAO.save(h4);
			hospitalDAO.save(h5);
			hospitalDAO.save(h6);
			hospitalDAO.save(h7);
			hospitalDAO.save(h8);
			hospitalDAO.save(h9);
			hospitalDAO.save(h10);
			hospitalDAO.save(h11);
			hospitalDAO.save(h12);
			hospitalDAO.save(h13);
			hospitalDAO.save(h14);
			hospitalDAO.save(h15);
			hospitalDAO.save(h16);
			hospitalDAO.save(h17);
	
		}
	}

}
