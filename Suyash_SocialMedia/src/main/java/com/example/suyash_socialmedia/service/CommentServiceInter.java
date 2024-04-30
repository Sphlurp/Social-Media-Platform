package com.example.suyash_socialmedia.service;

import com.example.suyash_socialmedia.DTO.CreateCommentRequest;
import com.example.suyash_socialmedia.DTO.UpdateCommentRequest;
import org.springframework.http.ResponseEntity;

public interface CommentServiceInter {
    ResponseEntity<?> createComment(CreateCommentRequest createCommentRequest);
    ResponseEntity<?> retrieveComment(int CommentID);
    ResponseEntity<?> updateComment(UpdateCommentRequest updateCommentRequest);
    ResponseEntity<?> deleteComment(int commentID);
}
