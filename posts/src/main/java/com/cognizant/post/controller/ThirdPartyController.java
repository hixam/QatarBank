package com.cognizant.post.controller;

import com.cognizant.post.FeignClient.QueryClient;
import com.cognizant.post.domain.Comment;
import com.cognizant.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ThirdPartyController {

    @Autowired
    QueryClient queryClient;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/all")
    public List<Post> getAllPosts(){
        return queryClient.getAllPosts();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable("id") String id){
        return queryClient.getPostById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/comment/{id}")
    public Comment getCommentById(@PathVariable("id") String id){
        return queryClient.getCommentById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/document/{pdfId}")
    public List<Post> getPostByDocumentId(@PathVariable("pdfId") String pdfId){
      return queryClient.getAllPosts().parallelStream().filter(o -> o.getPdfDocument().getId().equals(pdfId)).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/comment/post/{postId}")
    public List<Comment> getCommentByPostId(@PathVariable("postId") String postId){
      return queryClient.getAllComments().parallelStream().filter(o -> o.getPostId().equals(postId)).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/comment/document/{documentId}")
    public List<Comment> getCommentByDocumentId(@PathVariable("documentId") String documentId){
      return queryClient.getAllComments().parallelStream().filter(o -> o.getDocumentId().equals(documentId)).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/comment/new")
    public Comment createComment(@RequestBody Comment comment){
        return queryClient.createComment(comment);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/post/new")
    public Post createPost(@RequestBody Post post){
        return queryClient.createPost(post);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/test")
    public String test(){
        return "asd";
    }

}
