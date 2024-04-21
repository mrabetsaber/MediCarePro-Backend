package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Appointments;
import com.application.model.Patient;
import com.application.service.AppointmentsService;
import com.application.service.PatientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("appointement")
public class AppointementController {
	
	
	@Autowired
	private AppointmentsService appointmentsService;
	
	@Autowired
	private PatientService patientService;
	@PostMapping("/addAppointement")
	public String addNewPatient(@RequestBody Appointments appointement) throws Exception
	{
		Patient patient = new Patient();
		if(appointement.getPatient().getId()!=null) {
			
			 patient = patientService.findById(appointement.getPatient().getId());
		}
		else {
			patient=	patientService.savePrescriptions(appointement.getPatient());
		}
		
		
		appointement.setPatient(patient);
		
		appointmentsService.savePatient(appointement);
		return "modified successfully !!!";
	}
	

}
