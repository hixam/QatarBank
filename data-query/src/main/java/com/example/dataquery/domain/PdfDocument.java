package com.example.dataquery.domain;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PdfDocument {
    @Id
    private String id;
    private Binary data;
    private String title;
}
