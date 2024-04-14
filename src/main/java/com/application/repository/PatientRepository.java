package com.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.application.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
