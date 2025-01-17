package com.example.suyash_socialmedia.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserFeedResponseDTO {
    private int postID;
    private String postBody;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private List<CommentResponseDTO> comments;

    // Constructors, getters, and setters

    public UserFeedResponseDTO() {
        this.comments = new ArrayList<>();
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentResponseDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseDTO> comments) {
        this.comments = comments;
    }

    public void addComment(CommentResponseDTO comment) {
        this.comments.add(comment);
    }
}
