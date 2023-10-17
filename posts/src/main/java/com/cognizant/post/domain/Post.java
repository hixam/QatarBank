package com.cognizant.post.domain;

import lombok.Data;
import java.util.List;

@Data
public class Post {

    private String id;
    private String author;
    private List<Comment> comments;
    private PdfDocument pdfDocument;
    private String postText;
}
