package com.example.suyash_socialmedia.service;

import com.example.suyash_socialmedia.DTO.*;
import com.example.suyash_socialmedia.model.Comment;
import com.example.suyash_socialmedia.model.Post;
import com.example.suyash_socialmedia.model.userp.User1;
import com.example.suyash_socialmedia.repository.PostRepository;
import com.example.suyash_socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService implements PostServiceInter{

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createPost(CreatePostRequest createPostRequest) {
        int userId = createPostRequest.getUserID();
        Optional<User1> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User1 user = optionalUser.get();
            Post post = new Post();
            post.setPostBody(createPostRequest.getPostBody());
            post.setUser(user);
            post.setDate(new Date());
            postRepository.save(post);
            return ResponseEntity.ok().body("Post created successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @Override
    public ResponseEntity<?> retrievePost(int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            PostResponseDTO postResponseDTO = new PostResponseDTO();
            postResponseDTO.setPostID(post.getPostID());
            postResponseDTO.setPostBody(post.getPostBody());
            postResponseDTO.setDate(post.getDate());

            List<CommentResponseDTO> commentResponseDTOs = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
                commentResponseDTO.setCommentID(comment.getCommentID());
                commentResponseDTO.setCommentBody(comment.getCommentBody());

                // Retrieve details of the comment creator
                User1 creator = comment.getCommentCreator(userRepository);
                if (creator != null) {
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userResponseDTO.setUserID(creator.getId());
                    userResponseDTO.setName(creator.getName());
                    commentResponseDTO.setCommentCreator(userResponseDTO);
                }

                commentResponseDTOs.add(commentResponseDTO);
            }

            postResponseDTO.setComments(commentResponseDTOs);

            return ResponseEntity.ok().body(postResponseDTO);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    @Override
    public ResponseEntity<?> updatePost(UpdatePostRequest updatePostRequest) {
        Optional<Post> optionalPost = postRepository.findById(updatePostRequest.getPostID());
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostBody(updatePostRequest.getPostBody());
            postRepository.save(post);
            return ResponseEntity.ok().body("Post edited successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    @Override
    public ResponseEntity<?> deletePost(int postID) {
        Optional<Post> optionalPost = postRepository.findById(postID);
        if (optionalPost.isPresent()) {
            postRepository.deleteById(postID);
            return ResponseEntity.ok().body("Post deleted");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }
}
