package com.example.dataquery.controller;

import com.example.dataquery.domain.PdfDocument;
import com.example.dataquery.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;

    @PostMapping("/pdf/add")
    public ResponseEntity<PdfDocument> addPdf(@RequestParam("title") String title,
                           @RequestParam("file") MultipartFile pdf)
            throws IOException {
        return pdfService.addPdf(title, pdf).map(pdfDocument -> ResponseEntity.ok().body(pdfDocument)).orElseGet(() -> ResponseEntity.notFound()
                .build());
    }

    @DeleteMapping("/pdf/delete/{id}")
    public void deletePdf(@PathVariable("id") String id) {
        pdfService.deletePdf(id);
    }

    @PutMapping("/pdf/edit/{id}")
    public ResponseEntity<PdfDocument> updatePdf(@RequestParam("title") String title,
                           @PathVariable("id") String id) {
        return pdfService.updatePdf(id, title).map(pdfDocument -> ResponseEntity.ok().body(pdfDocument)).orElseGet(() -> ResponseEntity.notFound()
                .build());

    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String id) {

        return pdfService.getPdf(id).map(pdfDocument -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfDocument.getTitle() + "\"")
                .contentType(MediaType.valueOf(pdfDocument.getContentType()))
                .body(pdfDocument.getData())).orElseGet(() -> ResponseEntity.notFound()
                .build());

    }

    @GetMapping("/pdf/info/id")
    public List<String> getAllPdfsIds() {
        List<PdfDocument> allFiles = pdfService.getAllPdfs();
        return allFiles.stream().map(PdfDocument::getId).collect(Collectors.toList());
    }

    @GetMapping("/pdf/info")
    public List<PdfDocument> getAllPdfsInfo() {
        return pdfService.getAllPdfs();
    }

    @GetMapping("/pdf/info/{id}")
    public Optional<PdfDocument> getPdfInfo(@PathVariable String id) {
        return pdfService.getPdf(id);
    }

    @GetMapping("/test")
        public String asd(){
        return "ok";
        }

}
