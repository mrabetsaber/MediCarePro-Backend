package com.application.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Patient {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
    private String prenom ;
    private String region ;
    private String numTel; 
    @Temporal(TemporalType.DATE)
    private Calendar dateNaissance;
    private String profession; 
    private String couvertureSocial ;
    private String gender ;
    private String numDossier;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
	private List<Appointment> appointments;
	
	 public String getNumDossier() {
			return numDossier;
		}
		public void setNumDossier(String numDossier) {
			this.numDossier = numDossier;
		}
	
	
	
	
	
	
	
	
    

	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public Patient() {
		super();
	}
	public Patient(Long id, String nom, String prenom, String region, String numTel, Calendar dateNaissance,
			String profession, String couvertureSocial, String gender) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.region = region;
		this.numTel = numTel;
		this.dateNaissance = dateNaissance;
		this.profession = profession;
		this.couvertureSocial = couvertureSocial;
		this.gender = gender;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public Calendar getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Calendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getCouvertureSocial() {
		return couvertureSocial;
	}
	public void setCouvertureSocial(String couvertureSocial) {
		this.couvertureSocial = couvertureSocial;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
