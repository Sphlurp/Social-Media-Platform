package com.example.suyash_socialmedia.controller;

import com.example.suyash_socialmedia.DTO.CreatePostRequest;
import com.example.suyash_socialmedia.DTO.UpdatePostRequest;
import com.example.suyash_socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest1) {
        return postService.createPost(createPostRequest1);
    }

    @GetMapping
    public ResponseEntity<?> retrievePost(@RequestParam int postID) {
        return postService.retrievePost(postID);
    }

    @PatchMapping
    public ResponseEntity<?> updatePost(@RequestBody UpdatePostRequest updatePostRequest) {
        return postService.updatePost(updatePostRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam int postID) {
        return postService.deletePost(postID);
    }
}
