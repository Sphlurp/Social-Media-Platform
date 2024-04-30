package com.example.suyash_socialmedia.controller;

import com.example.suyash_socialmedia.DTO.UserDetailsDTO;
import com.example.suyash_socialmedia.model.userp.User1;
import com.example.suyash_socialmedia.model.userp.UserLogin;
import com.example.suyash_socialmedia.model.userp.UserSignup;
import com.example.suyash_socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignup user) {
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin user) {
        if (userService.login(user) == 2) {
            return ResponseEntity.ok("Login Successful");
        } else if (userService.login(user) == 0) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Username/Password Incorrect");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username/Password Incorrect");
        }
        else{
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam int userID) {
        return userService.getUserDetails(userID);
    }

    @GetMapping
    public ResponseEntity<?> getUserFeed() {
        return userService.getUserFeed();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

}
