package com.example.socialnetwork.services;

import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.DTOs.UserDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    ResponseEntity<?> checkUserDetails(UserDTO userDTO, HttpServletRequest request);
}
