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
import java.util.Optional;

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
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
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

    @GetMapping("/test")
        public String asd(){
        return "ok";
        }

}
