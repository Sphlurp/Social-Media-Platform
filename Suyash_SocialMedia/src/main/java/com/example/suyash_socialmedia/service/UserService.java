package com.example.suyash_socialmedia.service;

import com.example.suyash_socialmedia.DTO.CommentResponseDTO;
import com.example.suyash_socialmedia.DTO.UserFeedResponseDTO;
import com.example.suyash_socialmedia.DTO.UserResponseDTO;
import com.example.suyash_socialmedia.model.Post;
import com.example.suyash_socialmedia.model.Comment;
import com.example.suyash_socialmedia.model.userp.User1;
import com.example.suyash_socialmedia.model.userp.UserLogin;
import com.example.suyash_socialmedia.model.userp.UserSignup;
import com.example.suyash_socialmedia.repository.PostRepository;
import com.example.suyash_socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.suyash_socialmedia.DTO.UserDetailsDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInter{

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public ResponseEntity<?> signUp(UserSignup user_s) {

        if (userRepository.existsByEmail(user_s.getEmail())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.CONFLICT).body("Forbidden, Account already exists");
        } else {
            User1 user = new User1(user_s.getEmail(), user_s.getName(), user_s.getPassword());
            userRepository.save(user);
            return ResponseEntity.ok().body("Account Creation Successful");
        }
    }

    @Override
    public int login(UserLogin user) {
        int login_flag = 0;
        User1 existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            login_flag = 1;
        }
        else if(existingUser.getPassword().equals(user.getPassword())){
            login_flag = 2;
        }
        return login_flag;
    }

    @Override
    public ResponseEntity<?> getUserDetails(int userID) {
        Optional<User1> optionalUser = userRepository.findById(userID);
        if (optionalUser.isPresent()) {
            User1 user = optionalUser.get();
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user.getId(), user.getName(), user.getEmail());
            return ResponseEntity.ok().body(userDetailsDTO);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @Override
    public ResponseEntity<?> getUserFeed() {
       // List<Post> posts = postRepository.findAllByOrderByDateDesc();
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "postID"));;
        // Convert each Post to UserFeedResponseDTO
        List<UserFeedResponseDTO> userFeed = posts.stream()
                .map(this::convertPostToUserFeedResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(userFeed);
    }

    // Helper method to convert Post to UserFeedResponseDTO
    private UserFeedResponseDTO convertPostToUserFeedResponseDTO(Post post) {
        UserFeedResponseDTO userFeedResponseDTO = new UserFeedResponseDTO();
        userFeedResponseDTO.setPostID(post.getPostID());
        userFeedResponseDTO.setPostBody(post.getPostBody());
        userFeedResponseDTO.setDate(post.getDate()); // Convert date to string

        // Convert each comment in the post to CommentResponseDTO
        List<CommentResponseDTO> commentResponseDTOs = post.getComments().stream()
                .map(this::convertCommentToCommentResponseDTO)
                .collect(Collectors.toList());

        userFeedResponseDTO.setComments(commentResponseDTOs);

        return userFeedResponseDTO;
    }

    // Helper method to convert Comment to CommentResponseDTO
    private CommentResponseDTO convertCommentToCommentResponseDTO(Comment comment) {
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setCommentID(comment.getCommentID());
        commentResponseDTO.setCommentBody(comment.getCommentBody());

        // Fetch comment creator using getCommentCreator method
        User1 commentCreator = comment.getCommentCreator(userRepository);
        if (commentCreator != null) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUserID(commentCreator.getId());
            userResponseDTO.setName(commentCreator.getName());
            commentResponseDTO.setCommentCreator(userResponseDTO);
        }

        return commentResponseDTO;
    }

    @Override
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        List<User1> users = userRepository.findAll();
        List<UserDetailsDTO> userListDTOs = users.stream()
                .map(user -> new UserDetailsDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userListDTOs);
    }

}
