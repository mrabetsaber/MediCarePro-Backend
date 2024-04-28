package com.application.dto;

import java.util.Calendar;
import java.util.List;

public class OperationDto {
	
	private Long id;
	
	private Calendar date;
	private String observation;
    private List<DocumentDTO> documents;
    private int appointmentId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	public List<DocumentDTO> getDocuments() {
		return documents;
	}
	public void setDocuments(List<DocumentDTO> documents) {
		this.documents = documents;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
}
