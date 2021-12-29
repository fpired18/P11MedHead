package com.openclassrooms.MedHead_Platform.controller;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.MedHead_Platform.Classe.HospitalWithDisponibilityRequest;
import com.openclassrooms.MedHead_Platform.entity.Hospital;
import com.openclassrooms.MedHead_Platform.service.HospitalService;

@RestController
public class MainController {

	@Autowired
	private HospitalService hospitalService;

//************************Zone_Tests**********************************************************************************************************

	@GetMapping("/hospital/specialitycity")
	public List<String> getSpecialityByCity(@RequestParam String city) {
		List<String> speciality = hospitalService.findBySpecialitiesByCity(city);
		return speciality;
	}

	@GetMapping("/hospital/cityspeciality")
	public List<String> getCityBySpeciality(@RequestParam String speciality) {
		List<String> city = hospitalService.findByCityBySpecialities(speciality);
		return city;
	}

	@GetMapping("/hospital/groupscity")
	public List<String> getGroupsByCity(@RequestParam String city) {
		List<String> groups = hospitalService.findByGroupsByCity(city);
		return groups;
	}

	@GetMapping("/hospital/namecity")
	public List<String> getNameByCity(@RequestParam String city) {
		List<String> name = hospitalService.findByNameByCity(city);
		return name;
	}

	@GetMapping("/hospital/bedsacity")
	public List<Integer> getBedsaByCity(@RequestParam String city) {
		List<Integer> bedsa = hospitalService.findByBedsaByCity(city);
		return bedsa;
	}

	@GetMapping("/hospital/bedscity")
	public List<Integer> getBedsByCity(@RequestParam String city) {
		List<Integer> bed = hospitalService.findByBedsByCity(city);
		return bed;
	}

	@GetMapping("/hospital/loncity")
	public List<Double> getLonByCity(@RequestParam String city) {
		List<Double> lon = hospitalService.findByLonByCity(city);
		return lon;
	}

	@GetMapping("/hospital/latcity")
	public List<Double> getLatByCity(@RequestParam String city) {
		List<Double> lat = hospitalService.findByLatByCity(city);
		return lat;
	}

	// ********Nouveau*******************

	@GetMapping("/hospital/idid")
	public List<Hospital> getHospitalByid(@RequestParam Long id) {
		List<Hospital> hospitalById = hospitalService.findByHospitalById(id);
		return hospitalById;
	}

	@GetMapping("/hospital")
	public ResponseEntity<List<Hospital>> getAllHospitals() {
		try {
			List<Hospital> list = hospitalService.findAll();
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<Hospital>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Hospital>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/hospital")
	public ResponseEntity<Hospital> save(@RequestBody Hospital hospital) {
		try {
			return new ResponseEntity<>(hospitalService.save(hospital), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ********Nouveau*******************

	@GetMapping("/hospital2")
	public ResponseEntity<List<Hospital>> getAllHospitalsBySpeciality(@RequestParam String speciality) {
		try {
			List<Hospital> list = hospitalService.findByBySpeciality(speciality);
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<Hospital>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Hospital>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/hospital/speciality")
	public List<Hospital> getAllHospitalNumberOfBedsSpeciality(@RequestBody HospitalWithDisponibilityRequest request) {
		List<Hospital> bedsDispo = hospitalService.findByBySpeciality(request.getSpecialityRequest());
		List<Hospital> hospitalWithBeds = new ArrayList<Hospital>();
		Hospital nearestHospital = new Hospital();

		@SuppressWarnings("resource")
		Formatter fmt = new Formatter();
		double miniDistance = 5000000.0;
		double returnDistance = 0.0;
		for (Hospital item : bedsDispo) {
			if (item.bedsa == 1) {
				System.out.println(item.city + " dispose d'un lit disponible");
			}
			if (item.bedsa == 0) {
				System.out.println(item.city + " dispose d'aucun lit disponible");
			}
			if (item.bedsa > 1) {
				System.out.println("dispose de: " + item.bedsa + " lits disponibles");
			}
			if (item.bedsa > 0) {

				returnDistance = hospitalService.distanceGPS(request.getLatPatient(), request.getLonPatient(), item.lat,
						item.lon);
				item.distance = returnDistance;
				hospitalWithBeds.add(item);
				if (returnDistance <= miniDistance) {
					System.out.println("\nVoici miniDistance avant affectation: " + miniDistance);
					miniDistance = returnDistance;
					System.out.println("et après: " + miniDistance);
					nearestHospital = item;
					System.out.println("\nVoici item: " + item.city);
					System.out.println("L'hôpital le plus proche est: " + nearestHospital.city);
				}
			}

		}

		// trier ici

		System.out.println("\n*********************************************************");
		System.out.println("L'hôpital le plus proche est celui de: " + nearestHospital.city);
		System.out.println("La distance qui le sépare du patient est de: " + fmt.format("%.2f", miniDistance) + " Km");
		System.out.println("******************************************************\n");
		return hospitalWithBeds;
	}

//************************Zone_Tests***********************************************************************************************************	

}
