package com.application.dto;

import java.time.LocalDateTime;
import java.util.Calendar;

public class AppointmentDTO {
    private int id;
    private Calendar dateCreation;
    private LocalDateTime timestamp;
    private PatientDTO patient; // Representing patient information in the appointment

    // Constructors
    public AppointmentDTO() {
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

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public AppointmentDTO(int id, Calendar dateCreation, LocalDateTime timestamp, PatientDTO patient) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.timestamp = timestamp;
        this.patient = patient;
    }

    // Getters and setters
    // You can generate getters and setters using your IDE or manually implement them
}

