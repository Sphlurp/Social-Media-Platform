package com.example.suyash_socialmedia.controller;

import com.example.suyash_socialmedia.DTO.CreateCommentRequest;
import com.example.suyash_socialmedia.DTO.UpdateCommentRequest;
import com.example.suyash_socialmedia.model.Comment;
import com.example.suyash_socialmedia.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        return commentService.createComment(createCommentRequest);
    }

    @GetMapping
    public ResponseEntity<?> retrieveComment(@RequestParam int commentID) {
        return commentService.retrieveComment(commentID);
    }

    @PatchMapping
    public ResponseEntity<?> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest) {
        return commentService.updateComment(updateCommentRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestParam int commentID) {
        return commentService.deleteComment(commentID);
    }
}
