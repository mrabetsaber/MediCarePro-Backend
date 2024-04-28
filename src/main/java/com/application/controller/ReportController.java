package com.application.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.model.Patient;
import com.application.repository.PatientRepository;
import com.application.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
	private PatientRepository patientRepository;
    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateReport() throws JRException {
        List<Patient> data = new ArrayList<>();
        data.add(patientRepository.findAll().iterator().next());
        byte[] reportContent = reportService.generateReport(data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("report.pdf").build());

        return new ResponseEntity<>(reportContent, headers, HttpStatus.OK);
    }
}

