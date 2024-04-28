package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
