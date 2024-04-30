package com.example.suyash_socialmedia.model;

import com.example.suyash_socialmedia.model.userp.User1;
import com.example.suyash_socialmedia.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User1 user;

    // Getters and setters

    public int getUserId(){
        return this.user.getId();
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }

    // Method to retrieve comment creator details
    public User1 getCommentCreator(UserRepository userRepository) {
        Optional<User1> userOptional = userRepository.findById(user.getId());
        return userOptional.orElse(null);
    }

}
