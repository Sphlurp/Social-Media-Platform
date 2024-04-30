package com.example.suyash_socialmedia.service;

import com.example.suyash_socialmedia.DTO.CreatePostRequest;
import com.example.suyash_socialmedia.DTO.UpdatePostRequest;
import org.springframework.http.ResponseEntity;

public interface PostServiceInter {
    ResponseEntity<?> createPost(CreatePostRequest createPostRequest);
    ResponseEntity<?> retrievePost(int postId);
    ResponseEntity<?> updatePost(UpdatePostRequest updatePostRequest);
    ResponseEntity<?> deletePost(int postID);
}
