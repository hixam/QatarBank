package com.cognizant.post.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PdfDocument {

    private String id;
    private byte[] data;
    private String title;
    private String contentType;

}
