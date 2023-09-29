package com.example.socialnetwork.controllers;

import com.example.socialnetwork.services.UserService;
import com.example.socialnetwork.models.DTOs.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration (@RequestBody UserDTO userDTO, HttpServletRequest request){
         return userService.checkUserDetails(userDTO, request);
    }
}
