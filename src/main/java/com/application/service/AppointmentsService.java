package com.application.service;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Appointment;
import com.application.model.Patient;
import com.application.repository.AppointmentsRepository;
import com.application.repository.PatientRepository;

@Service
public class AppointmentsService {
	@Autowired
	private AppointmentsRepository  appointmentsRepository;
	
	public Appointment savePatient(Appointment appointement)
	{
		appointement.setDateCreation(Calendar.getInstance());
		appointement.setTimestamp(LocalDateTime.now());
		
		return appointmentsRepository.save(appointement);
	}
	
}
