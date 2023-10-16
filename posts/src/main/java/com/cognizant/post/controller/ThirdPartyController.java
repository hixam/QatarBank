package com.cognizant.post.controller;

import com.cognizant.post.FeignClient.QueryClient;
import com.cognizant.post.domain.PdfDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThirdPartyController {

    @Autowired
    QueryClient queryClient;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/pdf/info/id")
    public List<PdfDocument> getAllPdfsIds(){
        return queryClient.getAllPdfsIds();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/pdf/info")
    public List<PdfDocument> getAllPdfsInfo(){
        return queryClient.getAllPdfsInfo();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/pdf/info/{id}")
    public ResponseEntity<PdfDocument> getPdfInfo(@PathVariable String id){
        return queryClient.getPdfInfo(id).map(pdfDocument -> ResponseEntity.ok().body(pdfDocument)).orElseGet(() -> ResponseEntity.notFound()
                .build());
    }


}
