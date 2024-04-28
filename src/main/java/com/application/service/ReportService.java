package com.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Patient;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	 private static final String REPORT_PATH = "C:/SABER-PROJECTS/reports/test.jrxml";

	    public byte[] generateReport(List<Patient> data) throws JRException {
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("reportTitle", "Your Report Title"); // Add any custom report parameters
	      //  parameters.put("dataSource", data); // Set the data source

	        JasperPrint jasperPrint = JasperFillManager.fillReport(REPORT_PATH, parameters, new JRBeanCollectionDataSource(data));
	        return JasperExportManager.exportReportToPdf(jasperPrint);
	    }
}
