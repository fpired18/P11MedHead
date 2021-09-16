package com.openclassrooms.MedHead_Platform.controller;

import java.util.ArrayList;
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

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

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

		all.forEach(p -> sb.append(p.getSpecialityGroup() + "<br"));

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

	@GetMapping("/hospital/numberOfBedsAvailable/{numberOfBedsAvailable}")
	public List<Hospital> getSingleAvalaibleBeds(@PathVariable int numberOfBedsAvailable) {
		List<Hospital> patient = hospitalDAO.findByNumberOfBedsAvailable(numberOfBedsAvailable);
		return patient;
	}

	@GetMapping("/hospital/geographicalPosition/{geographicalPositionLong}")
	public List<Hospital> getSingleGeographicalPositionLong(@PathVariable int geographicalPositionLong) {
		List<Hospital> patient = hospitalDAO.findByGeographicalPositionLong(geographicalPositionLong);
		int tripDistance = hospitalDAO.distanceGPS(5, 8, 4, 2);
		System.out.println("Voici la distance "+ tripDistance);
		return patient;

	}

	@GetMapping("/hospital/geographicalPosition/{geographicalPositionLat}")
	public List<Hospital> getSingleGeographicalPositionLat(@PathVariable int geographicalPositionLat) {
		List<Hospital> patient = hospitalDAO.findByGeographicalPositionLat(geographicalPositionLat);
		return patient;

	}
	
	@GetMapping("/hospital/numberOfBedsAvailable")
	public List<Hospital> getAllHospitalWithDisponibility(){
		List<Hospital> bedsDispo = hospitalDAO.findAll();
		List<Hospital> hospitalWithBeds = new ArrayList<Hospital>();
		for(Hospital item: bedsDispo) {
			if (item.numberOfBedsAvailable > 10) {
			hospitalWithBeds.add(item);
			}
		}
		return hospitalWithBeds;
	}
	
	//@PostMapping
	

}
