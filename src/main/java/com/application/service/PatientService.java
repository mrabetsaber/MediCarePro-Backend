package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Patient;
import com.application.repository.PatientRepository;

@Service
public  class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public Patient savePrescriptions(Patient prescription)
	{
		return patientRepository.save(prescription);
	}
	
//	public List<Prescription> getPrescriptionByPatientname(String patientname)
//	{
//		return (List<Prescription>)patientRepository.findByPatientname(patientname);
//	}

	public List<Patient> getAllPrescriptions()
	{
		return (List<Patient>)patientRepository.findAll();
	}
}
