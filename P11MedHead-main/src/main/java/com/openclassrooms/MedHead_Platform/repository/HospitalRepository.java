package com.openclassrooms.MedHead_Platform.repository;

import com.openclassrooms.MedHead_Platform.entity.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{
	// Tu es nulle 
}
