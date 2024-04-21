package com.application.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.dto.PatientDTO;
import com.application.model.Patient;
import com.application.service.PatientService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	@PostMapping("/addPatient")
	public String addNewPatient(@RequestBody Patient slots) throws Exception
	{
		patientService.savePrescriptions(slots);
		return "modified successfully !!!";
	}
	
	
	@GetMapping("/patientlist")
	public ResponseEntity<List<Patient>> getPatient() throws Exception
	{
		List<Patient> doctors = patientService.getAllPrescriptions();
		return new ResponseEntity<List<Patient>>(doctors, HttpStatus.OK);
	}
	
	 @PostMapping("/search")
	    public List<Patient> searchPatientsByObject(@RequestBody Patient patient) {
	        // Call the service method passing the patient object
	        return patientService.searchPatientsByObject(patient);
	    }
	 
	 @GetMapping("/searchPatientsByNumTel")
	    public List<PatientDTO> searchPatientsByNumTel(@RequestParam String numTel) {
	        return patientService.findByNumTel(numTel);
	    }
	 @PostMapping("/verifyPatient")
	    public List<PatientDTO> verifyPatient(@RequestBody PatientDTO patientDTO) throws ParseException {
	        return patientService.verifyPatient(patientDTO);
	    }
	 
}
