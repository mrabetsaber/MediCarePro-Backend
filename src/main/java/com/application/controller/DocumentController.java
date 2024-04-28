package com.application.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.dto.DocumentDTO;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("documents")
public class DocumentController {

	 @Value("${file.upload.directory}")
	    private String uploadDirectory;

	    @PostMapping("/upload")
	    public ResponseEntity<DocumentDTO> uploadFile(@RequestParam("file") MultipartFile file) {
	        if (file.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }

	        try {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadDirectory + File.separator + fileName;

	            // Save the file to the specified location
	            File dest = new File(filePath);
	            file.transferTo(dest);

	            // Create a DocumentDTO and populate it with the relevant information
	            DocumentDTO documentDTO = new DocumentDTO();
	            documentDTO.setName(fileName);
	            documentDTO.setContentType(file.getContentType());
	            documentDTO.setSize(file.getSize());
	            // Populate any other attributes as needed

	            return ResponseEntity.status(HttpStatus.CREATED).body(documentDTO);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	    
	    @GetMapping("/download/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
	        try {
	            // Load file as Resource
	            Path filePath = Paths.get(uploadDirectory).resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());

	            // Check if file exists
	            if (resource.exists()) {
	                // Set content type based on file extension
	                String contentType = "application/octet-stream"; // Default content type
	                MediaType mediaType = MediaType.parseMediaType(contentType);

	                // Set response headers
	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(mediaType);
	                headers.setContentDispositionFormData("attachment", fileName);
	                return ResponseEntity.ok()
	                        .headers(headers)
	                        .body(resource);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	    
	    
	    
}
