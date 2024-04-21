package com.application.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.application.model.Patient;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
public interface PatientRepository extends CrudRepository<Patient, Long> {

	
	  List<Patient> findByNomContainingAndPrenomContainingAndRegionContainingAndNumTelContainingAndProfessionContainingAndCouvertureSocialContainingAndGenderContaining(
		        String nom, String prenom, String region, String numTel, String profession, String couvertureSocial, String gender);
		
	  List<Patient> findByNumTel(String numTel);
	  List<Patient> findByNumTelAndRegionAndDateNaissance(String numTel, String region, Calendar dateNaissance);
	   @Query("SELECT COUNT(p) FROM Patient p WHERE CAST(SUBSTRING(p.numDossier, 3, 4) AS integer) = :year")
	    int countByYear(@Param("year") int year);
	  
}
