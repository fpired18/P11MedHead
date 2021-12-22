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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.MedHead_Platform.Classe.HospitalWithDisponibilityRequest;
import com.openclassrooms.MedHead_Platform.entity.Hospital;
import com.openclassrooms.MedHead_Platform.entity.Hospital3;
import com.openclassrooms.MedHead_Platform.service.HospitalService;






@RestController
public class MainController {

	@Autowired
	private HospitalService hospitalService;

	/*@PostMapping("/hospital")
	public ResponseEntity<Hospital3> save(@RequestBody Hospital3 hospital) {
		try {
			return new ResponseEntity<>(hospitalService.save(hospital), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	/*@ResponseBody
	@RequestMapping("/")
	public String index() {
		Iterable<Hospital3> all = hospitalService.findAll();

		StringBuilder sb = new StringBuilder();

		all.forEach(p -> sb.append(p.getSpecialityGroup() + "<br/>"));
		return sb.toString();
	}*/

	

	@GetMapping("/hospital/{id}")
	public ResponseEntity<Hospital> getSingleHospital(@PathVariable Long id) {
		Optional<Hospital> patient = hospitalService.findById(id);
		if (patient.isPresent()) {
			return new ResponseEntity<Hospital>(patient.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Hospital>(HttpStatus.NOT_FOUND);

	}

	/*@GetMapping("/hospital/speciality/{speciality}")
	public List<Hospital3> getSingleSpeciality(@PathVariable String speciality) {
		List<Hospital3> patient = hospitalService.findBySpeciality(speciality);
		return patient;

	}*/
	
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
	
	//********Nouveau*******************
	
	
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
	
	
	//********Nouveau*******************
	
	
	
	
//************************Zone_Tests***********************************************************************************************************	

	/*@GetMapping("/hospital/specialityGroup/{specialityGroup}")
	public List<Hospital3> getSingleSpecialityGroup(@PathVariable String specialityGroup) {
		List<Hospital3> patient = hospitalService.findBySpecialityGroup(specialityGroup);
		return patient;

	}

	@GetMapping("/hospital/hospitalCenter/{hospitalCenter}")
	public List<Hospital3> getSingleHospitalCenter(@PathVariable String hospitalCenter) {
		List<Hospital3> patient = hospitalService.findByHospitalCenter(hospitalCenter);
		return patient;

	}

	@GetMapping("/hospital/numberOfBeds/{numberOfBeds}")
	public List<Hospital3> getSingleNumberOfBeds(@PathVariable int numberOfBeds) {
		List<Hospital3> patient = hospitalService.findByNumberOfBeds(numberOfBeds);
		return patient;

	}

	@GetMapping("/hospital/numberOfPatients/{numberOfPatients}")
	public List<Hospital3> getSingleNumberOfPatients(@PathVariable int numberOfPatients) {
		List<Hospital3> patient = hospitalService.findByNumberOfPatients(numberOfPatients);
		return patient;

	}

	@GetMapping("/hospital/numberOfPatients")
	public List<Hospital3> getSingleNumberOfPatientsAllHospital(@PathVariable int numberOfPatients) {
		List<Hospital3> patient = hospitalService.findByNumberOfPatients(numberOfPatients);
		return patient;

	}

	@GetMapping("/hospital/geographicalPositionLon/{geographicalPositionLon}")
	public List<Hospital3> getSingleGeographicalPositionLong(@PathVariable double geographicalPositionLon) {
		Formatter fmt = new Formatter();
		List<Hospital3> patient = hospitalService.findByGeographicalPositionLon(geographicalPositionLon);
		double tripDistance;
		try {
			tripDistance = hospitalService.distanceGPS(5.5, 8.5, 4.5, 2.5);
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
	public List<Hospital3> getSingleGeographicalPositionLat(@PathVariable double geographicalPositionLat) {
		List<Hospital3> patient = hospitalService.findByGeographicalPositionLat(geographicalPositionLat);
		return patient;

	}

	@GetMapping("/hospital/speciality")
	public List<Hospital3> getAllHospitalNumberOfBedsSpeciality(@RequestBody HospitalWithDisponibilityRequest request) { //HospitalWithDisponibilityRequest
		List<Hospital3> bedsDispo = hospitalService.findAll();
		List<Hospital3> hospitalWithBeds = new ArrayList<Hospital3>();
		Hospital3 nearestHospital = new Hospital3();

		@SuppressWarnings("resource")
		Formatter fmt = new Formatter();
		double miniDistance = 5000000.0;
		double returnDistance = 0.0;
		for (Hospital3 item : bedsDispo) {
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

					returnDistance = hospitalService.distanceGPS(request.latPatient, request.lonPatient,
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
	public List<Hospital3> getAllNearestHospitals() {
		List<Hospital3> bedsDispo = hospitalService.findAll();
		List<Hospital3> hospitalWithBeds = new ArrayList<Hospital3>();
		for (Hospital3 item : bedsDispo) {
			if (item.numberOfBedsAvailable >= 24) {
				hospitalWithBeds.add(item);
			}
		}
		return hospitalWithBeds;
	}*/

}
