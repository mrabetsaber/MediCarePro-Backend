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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.dto.AppointmentDTO;
import com.application.dto.DocumentDTO;
import com.application.dto.OperationDto;
import com.application.dto.PatientDTO;
import com.application.model.Appointment;
import com.application.model.Document;
import com.application.model.Operation;
import com.application.model.Patient;
import com.application.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	private static final String DATE_CALENDAR_FORMAT = "EEE MMM dd yyyy";
	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

	public Patient savePatient(PatientDTO patient) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		if (!isNotEmty(patient.getNumDossier())) {
			
		    int count = patientRepository.countByYear(currentYear);
		    String numYear = String.format("%02d%d", (count + 1), currentYear);
		    patient.setNumDossier(numYear);
		}
		
		
		return patientRepository.save(convertToDomain(patient));
    }
	private Patient convertToDomain(PatientDTO patientDTO) {
	    Patient patient = new Patient();
	    patient.setId(patientDTO.getId());
	    patient.setNom(patientDTO.getNom());
	    patient.setPrenom(patientDTO.getPrenom());
	    patient.setRegion(patientDTO.getRegion());
	    patient.setNumTel(patientDTO.getNumTel());
	    patient.setDateNaissance(patientDTO.getDateNaissance());
	    patient.setProfession(patientDTO.getProfession());
	    patient.setCouvertureSocial(patientDTO.getCouvertureSocial());
	    patient.setGender(patientDTO.getGender());
	    patient.setNumDossier(patientDTO.getNumDossier());

	    List<Appointment> appointments = patientDTO.getAppointments().stream()
	            .map(app->convertAppointmentDTOToDomain(app,patient))
	            .collect(Collectors.toList());
	    patient.setAppointments(appointments);
	    return patient;
	}

	private Appointment convertAppointmentDTOToDomain(AppointmentDTO appointmentDTO,Patient patient) {
	    Appointment appointment = new Appointment();
	    appointment.setId(appointmentDTO.getId());
	    appointment.setDateCreation(appointmentDTO.getDateCreation());
	    appointment.setTimestamp(appointmentDTO.getTimestamp());
	    appointment.setPatient(patient);
	    if(appointmentDTO.getOperations()!=null) {
	   List<Operation> operations = appointmentDTO.getOperations().stream()
	            .map(op->convertOperationDTOToDomain(op,appointment))
	            .collect(Collectors.toList());
	    appointment.setOperations(operations);
	    }
	    return appointment;
	}
	
	private Operation convertOperationDTOToDomain(OperationDto operationDto,Appointment appointment ) {
	    Operation operation = new Operation();
	    operation.setId(operationDto.getId());
	    operation.setDate(operationDto.getDate());
	    operation.setAppointment(appointment);
	    List<Document> documents = operationDto.getDocuments().stream()
	            .map(doc->convertDocumentDTOToDomain(doc,operation))
	            .collect(Collectors.toList());
	    operation.setPieceJointe(documents);
	    return operation;
	}

	private Document convertDocumentDTOToDomain(DocumentDTO documentDTO,Operation operation) {
	    Document document = new Document();
	    document.setId(documentDTO.getId());
	    document.setName(documentDTO.getName());
	    document.setFilePath(documentDTO.getFilePath());
	    // Assuming you have a method to set the operation based on ID
	    document.setOperation(operation);
	    return document;
	}
	
	
	
	
	
	
	
	public List<Patient> getAllPrescriptions() {
		return (List<Patient>) patientRepository.findAll();
	}
	int pageNumber = 0; // Or the desired page number
	int pageSize = 10; // Or the desired page size

	Pageable pageable = PageRequest.of(pageNumber, pageSize);
	public List<Patient> searchPatientsByObject(PatientDTO   patientDto) {
		// Use the fields of the patient object to perform the search
		
			return patientRepository.findByAttributes(	patientDto.getNom(), 
					patientDto.getPrenom(), 
					patientDto.getRegion(), 
					patientDto.getNumTel(), 
					patientDto.getProfession(), 
					patientDto.getCouvertureSocial(), 
					patientDto.getGender(),
					patientDto.getDateNaissanceFilter());
		

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

	        List<AppointmentDTO> appointmentDTOs = patient.getAppointments().stream()
	                .map(this::convertAppointmentToDTO)
	                .collect(Collectors.toList());
	        patientDTO.setAppointments(appointmentDTOs);
	        return patientDTO;
	}
	
	
	private AppointmentDTO convertAppointmentToDTO(Appointment appointment) {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(appointment.getId());
		appointmentDTO.setDateCreation(appointment.getDateCreation());
		appointmentDTO.setTimestamp(appointment.getTimestamp());
		appointmentDTO.setPatientId(appointment.getPatient().getId());

		List<OperationDto> operationDtos = appointment.getOperations().stream().map(this::convertOperationToDTO)
				.collect(Collectors.toList());
		appointmentDTO.setOperations(operationDtos);
		return appointmentDTO;
	}
	
	
	private OperationDto convertOperationToDTO(Operation operation) {
	
		OperationDto  operationDto=new OperationDto();
		operationDto.setId(operation.getId());
		operationDto.setDate(operation.getDate());
		operationDto.setAppointmentId(operation.getAppointment().getId());
		List<DocumentDTO> documentDTOs = operation.getPieceJointe().stream().map(this::convertDocumentToDTO)
				.collect(Collectors.toList());
		operationDto.setDocuments(documentDTOs);
		return operationDto;
	}
	
	private DocumentDTO convertDocumentToDTO(Document document) {
	
	    DocumentDTO documentDTO = new DocumentDTO();
	    documentDTO.setId(document.getId());
	    documentDTO.setName(document.getName());
	    documentDTO.setFilePath(document.getFilePath());
	    documentDTO.setOperationId(document.getOperation().getId());
	    return documentDTO	;
	    
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
