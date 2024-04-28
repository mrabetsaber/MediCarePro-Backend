package com.application.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.model.Document;
import com.application.model.Operation;
import com.application.repository.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    public void saveDocument(MultipartFile file, Operation operation) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = uploadDirectory + File.separator + fileName;

        // Save the file to the specified location
        File dest = new File(filePath);
        file.transferTo(dest);

        // Create a new Document entity and set its attributes
        Document document = new Document();
        document.setName(fileName);
        document.setFilePath(filePath);
        document.setOperation(operation);

        // Save the Document entity to the database
        documentRepository.save(document);
    }
}
