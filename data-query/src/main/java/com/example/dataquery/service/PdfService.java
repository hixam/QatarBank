package com.example.dataquery.service;

import com.example.dataquery.domain.Bid;
import com.example.dataquery.domain.PdfDocument;
import com.example.dataquery.mongo.BidRepository;
import com.example.dataquery.mongo.PdfDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class PdfService {

    @Autowired
    PdfDocumentRepository pdfDocumentRepository;

    public String addPdf(String title, MultipartFile file) throws IOException {
        PdfDocument pdf = new PdfDocument();
        pdf.setTitle(title);
        pdf.setData(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        pdf = pdfDocumentRepository.insert(pdf);
        return pdf.getId();
    }

    public PdfDocument getPdf(String id) {
        return pdfDocumentRepository.findById(id).get();
    }

}
