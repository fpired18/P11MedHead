package com.openclassrooms.MedHead_Platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.MedHead_Platform.entity.Hospital;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long>{
	
}
