package com.cognizant.post.domain;

import lombok.Data;

@Data
public class PdfDocument {

    private String id;
    private byte[] data;
    private String title;
    private String contentType;

}
