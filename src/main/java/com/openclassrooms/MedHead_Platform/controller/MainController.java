package com.openclassrooms.MedHead_Platform.controller;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;
import com.openclassrooms.MedHead_Platform.Classe.*;

@RestController
public class MainController {

	@Autowired
	private HospitalDAO hospitalDAO;

	@PostMapping("/hospital")
	public ResponseEntity<Hospital> save(@RequestBody Hospital patient) {
		try {
			return new ResponseEntity<>(hospitalDAO.save(patient), HttpStatus.CREATED);
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

	// ----------------------------Nouveau
	// GetMapping----------------------------------------
	@GetMapping("/hospital/numberOfBeds/{hospitalCenter}")
	public List<Hospital> getSingleNumberOfBedsToCity(@PathVariable String hospitalCenter) {
		List<Hospital> patient = hospitalDAO.findByHospitalCenter(hospitalCenter);
		return patient;

	}

	@GetMapping("/hospital/numberOfPatients/{numberOfPatients}")
	public List<Hospital> getSingleNumberOfPatients(@PathVariable int numberOfPatients) {
		List<Hospital> patient = hospitalDAO.findByNumberOfPatients(numberOfPatients);
		return patient;

	}

	/*@GetMapping("/hospital/numberOfBedsAvailable/{numberOfBedsAvailable}")
	public List<Hospital> getSingleAvalaibleBeds(@PathVariable int numberOfBedsAvailable) {
		List<Hospital> patient = hospitalDAO.findByNumberOfBedsAvailable(numberOfBedsAvailable);
		return patient;
	}*/

	@GetMapping("/hospital/geographicalPositionLong/{geographicalPositionLong}")
	public List<Hospital> getSingleGeographicalPositionLong(@PathVariable double geographicalPositionLong) {
		Formatter fmt = new Formatter();
		List<Hospital> patient = hospitalDAO.findByGeographicalPositionLong(geographicalPositionLong);
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

	@PostMapping("/hospital/numberOfBedsAvailable")
	public List<Hospital> getAllHospitalWithDisponibility(@RequestBody HospitalWithDisponibilityRequest request) {
		List<Hospital> bedsDispo = hospitalDAO.findAll();
		List<Hospital> hospitalWithBeds = new ArrayList<Hospital>();
		Hospital nearestHospital = new Hospital();
		double miniDistance = 5000000.0;
		double returnDistance = 0.0;
		@SuppressWarnings("resource")
		var fmt = new Formatter();
		for (Hospital item : bedsDispo) {
			if (item.numberOfBedsAvailable > 0) {
				returnDistance = hospitalDAO.distanceGPS(request.latPatient, request.lonPatient, item.geographicalPositionLat,
						item.geographicalPositionLong); // lonGPS, distanceGPS, travelGPS, shortTravel
				System.out.println("\nVoici le returnDistance: " + returnDistance);
				System.out.println("Voici le miniDistance: " + miniDistance);
				System.out.println("\nVoici le request.latPatient + request.lonPatient: " + request.latPatient + " " + request.lonPatient);

				if (returnDistance <= miniDistance) {
					System.out.println("\nVoici le returnDistance dans le if: " + returnDistance);
					System.out.println("Voici le miniDistance dans le if avant l'affectation: " + miniDistance);
					miniDistance = returnDistance;
					System.out.println("\nVoici le miniDistance dans le if après l'affectation: " + miniDistance);
					nearestHospital = item;
					System.out.println("\nVoici item: " + item.hospitalCenter);
					System.out.println("Voici nearestHospital: " + nearestHospital.hospitalCenter);
				}
				hospitalWithBeds.add(item);
			}

		}
		System.out.println("\n***********************************");
		System.out.println("L'hôpital le plus proche est celui de: " + nearestHospital.hospitalCenter);
		System.out.println("La distance qui le sépare du patient est de: " + fmt.format("%.2f", miniDistance) + " Km");
		System.out.println("***********************************\n");
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
	
	// Ce requestMapping renvoi le "viewName" dans Postman !!!!!!!!!!!!!!!!!!!!!!!
		// Voici le GET de Postam localhost:9010/hospital/specialityGroup,
		// /hospital/speciality
		@RequestMapping(value = { "/hospital/specialityGroup, /hospital/speciality" }, method = RequestMethod.GET)
		public String search(@RequestParam Map<String, String> allrequestParams) {
			return "viewName";

		}

	// A controler !!!!!!!!!!!!!!!!!!!!!!!

	/*
	 * @GetMapping("/hospital/numberOfBedsAvailable/{numberOfBedsAvailable}") public
	 * List<Hospital> getNumberOfBedsAvailableBetween(int numMini, int numMaxi) {
	 * List<Hospital> bedsDispo = hospitalDAO.findAll(); List<Hospital>
	 * hospitalWithBeds = new ArrayList<Hospital>(); for(Hospital item: bedsDispo) {
	 * if (item.numberOfBedsAvailable > numMini || item.numberOfBedsAvailable <
	 * numMaxi ) { hospitalWithBeds.add(item); } } return hospitalWithBeds;
	 * 
	 * }
	 */

	

	/*
	 * @RequestMapping(value=
	 * "/hospital/numberOfBedsAvailable/{numberOfBedsAvailable}", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public String getNumberOfPatients(@PathVariable
	 * ("numberOfBedsAvailable") int id) { return "Get a specific Foo with id = " +
	 * id; }
	 */

	// @RequestMapping("/hospital/numberOfBedsAvailable/{numberOfBedsAvailable}")
	// public @ResponseBody int getNumberOfBedsAvailable@PathVariable(value=
	// "numberOfBedsAvailable") String Id,@RequestParam String
	// someNumberOfBedsAvailable){}

	/*
	 * @GetMapping("/hospital/numberOfBedsAvailable/{numberOfBedsAvailable}") public
	 * List<Hospital> getAllHospitalWithDisponibility2() { List<Hospital> bedsDispo
	 * = hospitalDAO.findAll(); List<Hospital> hospitalWithBeds = new
	 * ArrayList<Hospital>(); return hospitalWithBeds; }
	 * 
	 * @PutMapping("/hospital") public List<Hospital> updateHospital(@RequestBody
	 * Hospital hospital) { hospital.setId(id); return
	 * hospitalDAO.updateHospital(hospital); }
	 */

}
