package com.example.suyash_socialmedia.DTO;

import com.example.suyash_socialmedia.model.Comment;

public class CommentResponseDTO {
    private int commentID;
    private String commentBody;
    private UserResponseDTO commentCreator;

    public CommentResponseDTO() {}

    public CommentResponseDTO(int commentID, String commentBody, UserResponseDTO commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
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

    public UserResponseDTO getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(UserResponseDTO commentCreator) {
        this.commentCreator = commentCreator;
    }

}
