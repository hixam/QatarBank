package com.cognizant.post.domain;


import lombok.Data;

@Data
public class Comment {
    private String id;
    private String author;
    private String comment;
    private String postId;
    private String documentId;
}
