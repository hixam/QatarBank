package com.cognizant.post.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Post {

    @Id
    private String id;
    private String author;
    @DBRef
    private List<Comment> comments;
    @DBRef
    private PdfDocument pdfDocument;
    private String postTitle;
}
