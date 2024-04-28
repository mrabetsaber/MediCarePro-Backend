package com.application.repository;
import org.springframework.data.domain.Pageable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.application.model.Patient;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
public interface PatientRepository extends CrudRepository<Patient, Long> {

	 List<Patient> findByNomContainingAndPrenomContainingAndRegionContainingAndNumTelContainingAndProfessionContainingAndCouvertureSocialContainingAndGenderContaining(
	            String nom, String prenom, String region, String numTel, String profession, String couvertureSocial, String gender , Pageable pageable);
	  List<Patient> findByNomContainingAndPrenomContainingAndRegionContainingAndNumTelContainingAndProfessionContainingAndCouvertureSocialContainingAndGenderContainingAndDateNaissance(
		        String nom, String prenom, String region, String numTel, String profession, String couvertureSocial, String gender, Calendar dateNaissance , Pageable pageable);

	  
	  List<Patient> findByNumTel(String numTel);
	  List<Patient> findByNumTelAndRegionAndDateNaissance(String numTel, String region, Calendar dateNaissance);
	   @Query("SELECT COUNT(p) FROM Patient p WHERE CAST(SUBSTRING(p.numDossier, 3, 4) AS integer) = :year")
	    int countByYear(@Param("year") int year);
	   
	   
	   @Query("SELECT p FROM Patient p WHERE "
	            + "(LOWER(p.nom) LIKE LOWER(CONCAT('%', :nom, '%'))) AND "
	            + "(LOWER(p.prenom) LIKE LOWER(CONCAT('%', :prenom, '%'))) AND "
	            + "(LOWER(p.region) LIKE LOWER(CONCAT('%', :region, '%'))) AND "
	            + "(LOWER(p.numTel) LIKE LOWER(CONCAT('%', :numTel, '%'))) AND "
	            + "(LOWER(p.profession) LIKE LOWER(CONCAT('%', :profession, '%'))) AND "
	            + "(LOWER(p.couvertureSocial) LIKE LOWER(CONCAT('%', :couvertureSocial, '%'))) AND "
	            + "(LOWER(p.gender) LIKE LOWER(CONCAT('%', :gender, '%'))) AND "
	            + "(:dateNaissance IS NULL OR p.dateNaissance = :dateNaissance)")
	    List<Patient> findByAttributes(@Param("nom") String nom, 
	                                   @Param("prenom") String prenom,
	                                   @Param("region") String region,
	                                   @Param("numTel") String numTel,
	                                   @Param("profession") String profession,
	                                   @Param("couvertureSocial") String couvertureSocial,
	                                   @Param("gender") String gender,
	                                   @Param("dateNaissance") Calendar dateNaissance);
	
	  
}
