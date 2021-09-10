package com.openclassrooms.MedHead_Platform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;
import com.openclassrooms.MedHead_Platform.repository.HospitalRepository;

@Controller
public class MainController {

	@Autowired
	private HospitalDAO hospitalDAO;

	@Autowired
	HospitalRepository hospitalRepository;

	@ResponseBody
	@RequestMapping("/")
	public String index() {
		Iterable<Hospital> all = hospitalDAO.findAll();

		StringBuilder sb = new StringBuilder();

		all.forEach(p -> sb.append(p.getSpecialtyGroup() + "<br"));

		return sb.toString();
	}

	@GetMapping("/hospital")
	public ResponseEntity<List<Hospital>> getAllHospitals() {
		try {
			List<Hospital> list = hospitalRepository.findAll(); 
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
		Optional<Hospital> patient = hospitalRepository.findById(id);
		if (patient.isPresent()) {
			return new ResponseEntity<Hospital>(patient.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Hospital>(HttpStatus.NOT_FOUND);

	}

}
