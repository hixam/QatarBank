package com.example.dataquery.service;

import com.example.dataquery.domain.PdfDocument;
import com.example.dataquery.mongo.PdfDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PdfService {

    @Autowired
    PdfDocumentRepository pdfDocumentRepository;

    public Optional<PdfDocument> addPdf(String title, MultipartFile file) throws IOException {
        return Optional.of(pdfDocumentRepository.save(buildPdfEntity(title, file)));
    }

    private PdfDocument buildPdfEntity(String title, MultipartFile file) throws IOException {
        return PdfDocument.builder()
                .data(file.getBytes())
                .id(String.valueOf(UUID.randomUUID()))
                .title(title)
                .contentType(file.getContentType())
                .build();
    }

    public Optional<PdfDocument> getPdf(String id) {
        return pdfDocumentRepository.findById(id);
    }

    public void deletePdf(String id){
        pdfDocumentRepository.deleteById(id);
    }

    public Optional<PdfDocument> updatePdf(String id, String newFileName) {
        Optional<PdfDocument> pdfToUpdate = getPdf(id);
        if (pdfToUpdate.isPresent()){
            pdfToUpdate.get().setTitle(newFileName);
            return Optional.of(pdfDocumentRepository.save(pdfToUpdate.get()));
        }
        return Optional.empty();
    }

    public List<PdfDocument> getAllPdfs() {
        return pdfDocumentRepository.findAll();
    }

}
