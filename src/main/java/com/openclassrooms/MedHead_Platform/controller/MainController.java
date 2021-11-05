package com.openclassrooms.MedHead_Platform.controller;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.MedHead_Platform.Classe.HospitalWithDisponibilityRequest;
import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

@RestController
public class MainController {

	@Autowired
	private HospitalDAO hospitalDAO;

	@PostMapping("/hospital")
	public ResponseEntity<Hospital> save(@RequestBody Hospital hospital) {
		try {
			return new ResponseEntity<>(hospitalDAO.save(hospital), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping("/")
	public String index() {
		Iterable<Hospital> all = hospitalDAO.findAll();

		StringBuilder sb = new StringBuilder();

		all.forEach(p -> sb.append(p.getSpecialityGroup() + "<br/>"));

		return sb.toString();
	}

	@GetMapping("/hospital")
	public ResponseEntity<List<Hospital>> getAllHospitals() {
		try {
			List<Hospital> list = hospitalDAO.findAll();
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<Hospital>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Hospital>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/hospital/{id}")
	public ResponseEntity<Hospital> getSingleHospital(@PathVariable Long id) {
		Optional<Hospital> patient = hospitalDAO.findById(id);
		if (patient.isPresent()) {
			return new ResponseEntity<Hospital>(patient.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Hospital>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/hospital/speciality/{speciality}")
	public List<Hospital> getSingleSpeciality(@PathVariable String speciality) {
		List<Hospital> patient = hospitalDAO.findBySpeciality(speciality);
		return patient;

	}

	@GetMapping("/hospital/specialityGroup/{specialityGroup}")
	public List<Hospital> getSingleSpecialityGroup(@PathVariable String specialityGroup) {
		List<Hospital> patient = hospitalDAO.findBySpecialityGroup(specialityGroup);
		return patient;

	}

	@GetMapping("/hospital/hospitalCenter/{hospitalCenter}")
	public List<Hospital> getSingleHospitalCenter(@PathVariable String hospitalCenter) {
		List<Hospital> patient = hospitalDAO.findByHospitalCenter(hospitalCenter);
		return patient;

	}

	@GetMapping("/hospital/numberOfBeds/{numberOfBeds}")
	public List<Hospital> getSingleNumberOfBeds(@PathVariable int numberOfBeds) {
		List<Hospital> patient = hospitalDAO.findByNumberOfBeds(numberOfBeds);
		return patient;

	}

	@GetMapping("/hospital/numberOfPatients/{numberOfPatients}")
	public List<Hospital> getSingleNumberOfPatients(@PathVariable int numberOfPatients) {
		List<Hospital> patient = hospitalDAO.findByNumberOfPatients(numberOfPatients);
		return patient;

	}

	@GetMapping("/hospital/numberOfPatients")
	public List<Hospital> getSingleNumberOfPatientsAllHospital(@PathVariable int numberOfPatients) {
		List<Hospital> patient = hospitalDAO.findByNumberOfPatients(numberOfPatients);
		return patient;

	}

	@GetMapping("/hospital/geographicalPositionLon/{geographicalPositionLon}")
	public List<Hospital> getSingleGeographicalPositionLong(@PathVariable double geographicalPositionLon) {
		Formatter fmt = new Formatter();
		List<Hospital> patient = hospitalDAO.findByGeographicalPositionLon(geographicalPositionLon);
		double tripDistance;
		try {
			tripDistance = hospitalDAO.distanceGPS(5.5, 8.5, 4.5, 2.5);
			System.out.println("\n***********************************");
			System.out.println("Voici la distance " + fmt.format("%.2f", tripDistance) + " Km");
			System.out.println("***********************************\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (fmt != null) {
				fmt.close();
			}
		}
		return patient;
	}

	@GetMapping("/hospital/geographicalPositionLat/{geographicalPositionLat}")
	public List<Hospital> getSingleGeographicalPositionLat(@PathVariable double geographicalPositionLat) {
		List<Hospital> patient = hospitalDAO.findByGeographicalPositionLat(geographicalPositionLat);
		return patient;

	}

	@GetMapping("/hospital/speciality")
	public List<Hospital> getAllHospitalNumberOfBedsSpeciality(@RequestBody HospitalWithDisponibilityRequest request) {
		List<Hospital> bedsDispo = hospitalDAO.findAll();
		List<Hospital> hospitalWithBeds = new ArrayList<Hospital>();
		Hospital nearestHospital = new Hospital();

		@SuppressWarnings("resource")
		Formatter fmt = new Formatter();
		double miniDistance = 5000000.0;
		double returnDistance = 0.0;
		for (Hospital item : bedsDispo) {
			if (item.speciality.equals(request.specialityRequest) && request.specialityRequest != "") {
				System.out.println("\n***********************************");
				System.out.println("L'hôpital de: " + item.hospitalCenter);
				if (item.numberOfBedsAvailable == 1) {
					System.out.println("dispose d'un lit disponible");
				}
				if (item.numberOfBedsAvailable == 0) {
					System.out.println("dispose d'aucun lit disponible");
				}
				if (item.numberOfBedsAvailable > 1) {
					System.out.println("dispose de: " + item.numberOfBedsAvailable + " lits disponibles");
				}
				System.out.println("dans la spécialité: " + item.speciality);
				if (item.numberOfBedsAvailable > 0) {

					returnDistance = hospitalDAO.distanceGPS(request.latPatient, request.lonPatient,
							item.geographicalPositionLat, item.geographicalPositionLon);
					item.distance = (int) returnDistance;
					;
					hospitalWithBeds.add(item);
					if (returnDistance <= miniDistance) {
						System.out.println("\nVoici miniDistance avant affectation: " + miniDistance);
						miniDistance = returnDistance;
						System.out.println("et après: " + miniDistance);
						nearestHospital = item;
						System.out.println("\nVoici item: " + item.hospitalCenter);
						System.out.println("L'hôpital le plus proche est: " + nearestHospital.hospitalCenter);
					}
				}
			}

		}
		System.out.println("\n*********************************************************");
		System.out.println("L'hôpital le plus proche est celui de: " + nearestHospital.hospitalCenter);
		System.out.println("La distance qui le sépare du patient est de: " + fmt.format("%.2f", miniDistance) + " Km");
		System.out.println("******************************************************\n");
		return hospitalWithBeds;
	}

	@GetMapping("/hospital/geographicalPosition")
	public List<Hospital> getAllNearestHospitals() {
		List<Hospital> bedsDispo = hospitalDAO.findAll();
		List<Hospital> hospitalWithBeds = new ArrayList<Hospital>();
		for (Hospital item : bedsDispo) {
			if (item.numberOfBedsAvailable >= 24) {
				hospitalWithBeds.add(item);
			}
		}
		return hospitalWithBeds;
	}

}
