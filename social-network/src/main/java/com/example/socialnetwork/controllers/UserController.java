package com.example.socialnetwork.controllers;

import com.example.socialnetwork.services.UserService;
import com.example.socialnetwork.models.DTOs.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registration (@RequestBody UserDTO userDTO){
         return ResponseEntity.status(200).body(userService.checkUserDetails(userDTO));
    }
}
