package com.application.dto;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class AppointmentDTO {
    private int id;
    private Calendar dateCreation;
    private LocalDateTime timestamp;
    private Long patientId; // Representing patient information in the appointment
    private List<OperationDto> operations;
    // Constructors
    public AppointmentDTO() {
    }

 
	public List<OperationDto> getOperations() {
		return operations;
	}


	public void setOperations(List<OperationDto> operations) {
		this.operations = operations;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	

	public AppointmentDTO(int id, Calendar dateCreation, LocalDateTime timestamp, PatientDTO patient) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.timestamp = timestamp;
    }

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

    // Getters and setters
    // You can generate getters and setters using your IDE or manually implement them
}

