package com.example.dataquery.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document
public class PdfDocument {
    @Id
    private String id;
    private byte[] data;
    private String title;
    private String contentType;
}
