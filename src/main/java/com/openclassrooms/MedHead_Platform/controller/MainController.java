package com.openclassrooms.MedHead_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openclassrooms.MedHead_Platform.dao.HospitalDAO;
import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Controller
public class MainController {
	
	@Autowired
	private HospitalDAO hospitalDAO;
	
	@ResponseBody
	@RequestMapping("/")
	public String index() {
		Iterable<Hospital> all=hospitalDAO.findAll();
		
		StringBuilder sb = new StringBuilder();
		
		all.forEach(p -> sb.append(p.getSpecialtyGroup() + "<br"));
		
		return sb.toString();
	}

}
