package com.example.socialnetwork.services;

import com.example.socialnetwork.models.DTOs.UserDTO;
import com.example.socialnetwork.security.jwt.JwtDTO;


public interface UserService {
    UserDTO checkUserDetails(UserDTO userDTO);
    JwtDTO checkUserDetailsInDB(UserDTO userDTO);
}
