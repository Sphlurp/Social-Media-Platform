package com.example.suyash_socialmedia.service;

import com.example.suyash_socialmedia.DTO.UserDetailsDTO;
import com.example.suyash_socialmedia.model.userp.UserLogin;
import com.example.suyash_socialmedia.model.userp.UserSignup;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInter {
    ResponseEntity<?> signUp(UserSignup user_s);
    int login(UserLogin user);
    ResponseEntity<?> getUserDetails(int userID);
    ResponseEntity<?> getUserFeed();
    ResponseEntity<List<UserDetailsDTO>> getAllUsers();

}
