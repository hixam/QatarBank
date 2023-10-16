package com.example.dataquery.controller;

import com.example.dataquery.domain.PdfDocument;
import com.example.dataquery.domain.ProductBidList;
import com.example.dataquery.mongo.CustomAggregationOperation;
import com.example.dataquery.mongo.ProductBidListRepository;
import com.example.dataquery.mongo.ProductRepository;
import com.example.dataquery.service.PdfService;
import com.example.dataquery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;

@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;

    @PostMapping("/pdf/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("file") MultipartFile pdf)
            throws IOException {
        String id = pdfService.addPdf(title, pdf);
        return "redirect:/photos/" + id;
    }

    @GetMapping("/pdf/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        PdfDocument pdf = pdfService.getPdf(id);
        model.addAttribute("title", pdf.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(pdf.getData().getData()));
        return "photos";
    }

    @GetMapping("/test")
        public String asd(){
        return "ok";
        }

}
