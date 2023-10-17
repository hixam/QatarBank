package com.cognizant.post.FeignClient;

import com.cognizant.post.domain.Comment;
import com.cognizant.post.domain.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "https://my-json-server.typicode.com/hixam/QatarBank", name = "thirdParty")
public interface QueryClient {

    @RequestMapping(method = RequestMethod.GET, path = "/posts")
    List<Post> getAllPosts();

    @RequestMapping(method = RequestMethod.GET, path = "/comments")
    List<Comment> getAllComments();

    @RequestMapping(method = RequestMethod.POST, path = "/comments/new")
    Comment createComment(@RequestBody Comment comment);

    @RequestMapping(method = RequestMethod.POST, path = "/posts/new")
    Post createPost(@RequestBody Post post);

    @RequestMapping(method = RequestMethod.GET, path = "/posts/{id}")
    Post getPostById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, path = "/comments/{id}")
    Comment getCommentById(@PathVariable("id") String id);



}

