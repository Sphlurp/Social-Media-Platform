package com.example.suyash_socialmedia.service;

import com.example.suyash_socialmedia.DTO.*;
import com.example.suyash_socialmedia.model.Comment;
import com.example.suyash_socialmedia.model.Post;
import com.example.suyash_socialmedia.model.userp.User1;
import com.example.suyash_socialmedia.repository.CommentRepository;
import com.example.suyash_socialmedia.repository.PostRepository;
import com.example.suyash_socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentServiceInter {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createComment(CreateCommentRequest createCommentRequest) {
        Optional<User1> optionalUser = userRepository.findById(createCommentRequest.getUserID());
        if (optionalUser.isPresent()) {
            Optional<Post> optionalPost = postRepository.findById(createCommentRequest.getPostID());
            if (optionalPost.isPresent()) {
                Comment comment = new Comment();
                comment.setCommentBody(createCommentRequest.getCommentBody());
                comment.setUser(optionalUser.get());
                comment.setPost(optionalPost.get());
                commentRepository.save(comment);
                return ResponseEntity.ok().body("Comment created successfully");
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("Error", "Post does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
                // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
            }
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @Override
    public ResponseEntity<?> retrieveComment(int CommentID) {
        Optional<Comment> optionalComment = commentRepository.findById(CommentID);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            Optional<User1> optionalUser = userRepository.findById(comment.getUserId());
            if (optionalUser.isPresent()) {
                User1 user = optionalUser.get();
                UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(), user.getName());
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment.getCommentID(), comment.getCommentBody(), userResponseDTO);
                return ResponseEntity.ok().body(commentResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user details");
            }
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }

    @Override
    public ResponseEntity<?> updateComment(UpdateCommentRequest updateCommentRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(updateCommentRequest.getCommentID());
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setCommentBody(updateCommentRequest.getCommentBody());
            commentRepository.save(comment);
            return ResponseEntity.ok().body("Comment edited successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }

    @Override
    public ResponseEntity<?> deleteComment(int commentID) {
        Optional<Comment> optionalComment = commentRepository.findById(commentID);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(commentID);
            return ResponseEntity.ok().body("Comment deleted");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }
}
