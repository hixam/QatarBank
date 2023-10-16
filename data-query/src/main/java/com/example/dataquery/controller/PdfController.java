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
import java.util.stream.Collectors;

@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;

    @PostMapping("/pdf/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("file") MultipartFile pdf)
            throws IOException {
        String id = pdfService.addPdf(title, pdf);
        return "Successfully uploaded PDS with id : " + id;
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String id) {
        Optional<PdfDocument> fileEntityOptional = pdfService.getPdf(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        PdfDocument fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getTitle() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
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
