package com.example.dataquery.mongo;

import com.example.dataquery.domain.PdfDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PdfDocumentRepository extends MongoRepository <PdfDocument, String> {
}
