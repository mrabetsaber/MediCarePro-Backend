package com.application.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dto.PatientDTO;
import com.application.model.Patient;
import com.application.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	private static final String DATE_CALENDAR_FORMAT = "EEE MMM dd yyyy";
	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

	public Patient savePrescriptions(Patient patient) {
        // Get the current year
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		// Check if the patient is a new patient or an existing one
		if (!isNotEmty(patient.getNumDossier())) {
		    // Query the database to find the number of patients registered in the current year
		    int count = patientRepository.countByYear(currentYear);

		    // Increment the count and generate the num-year identifier
		    String numYear = String.format("%02d%d", (count + 1), currentYear);

		    patient.setNumDossier(numYear);
		}
		return patientRepository.save(patient);
    }

//	public List<Prescription> getPrescriptionByPatientname(String patientname)
//	{
//		return (List<Prescription>)patientRepository.findByPatientname(patientname);
//	}

	public List<Patient> getAllPrescriptions() {
		return (List<Patient>) patientRepository.findAll();
	}

	public List<Patient> searchPatientsByObject(Patient patient) {
		// Use the fields of the patient object to perform the search
		return patientRepository
				.findByNomContainingAndPrenomContainingAndRegionContainingAndNumTelContainingAndProfessionContainingAndCouvertureSocialContainingAndGenderContaining(
						patient.getNom(), patient.getPrenom(), patient.getRegion(), patient.getNumTel(),
						patient.getProfession(), patient.getCouvertureSocial(), patient.getGender());
	}

	public Patient findById(Long id) {
		Optional<Patient> patientOptional = patientRepository.findById(id);
		return patientOptional.orElse(null); // Handle the case where patient is not found
	}

	public List<PatientDTO> findByNumTel(String numTel ) {
		List<Patient> patients = patientRepository.findByNumTel(numTel);
		return patients.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public List<PatientDTO> verifyPatient(PatientDTO patientDTO ) throws ParseException {
		

		
		List<Patient> patients = patientRepository.findByNumTelAndRegionAndDateNaissance(patientDTO.getNumTel(),patientDTO.getRegion(),patientDTO.getDateNaissanceFilter());
		return patients.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	private PatientDTO convertToDTO(Patient patient) {
		 PatientDTO patientDTO = new PatientDTO();
	        patientDTO.setId(patient.getId());
	        patientDTO.setNom(patient.getNom());
	        patientDTO.setPrenom(patient.getPrenom());
	        patientDTO.setRegion(patient.getRegion());
	        patientDTO.setNumTel(patient.getNumTel());
	        patientDTO.setDateNaissance(patient.getDateNaissance());
	        patientDTO.setProfession(patient.getProfession());
	        patientDTO.setCouvertureSocial(patient.getCouvertureSocial());
	        patientDTO.setGender(patient.getGender());
	        patientDTO.setNumDossier(patient.getNumDossier());
	      

	        return patientDTO;
	}
	
	private Calendar stringToCalendar(String dateString) throws ParseException {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
			dateCalendar = sdf.getCalendar();
		
				dateCalendar.setTime(sdf.parse(dateString));

			
		}

		return dateCalendar;
	}
	private Calendar calendarStringToCalendarObject(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_CALENDAR_FORMAT, Locale.ENGLISH);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}
	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value) && !value.equals("undefined");

	}
}
