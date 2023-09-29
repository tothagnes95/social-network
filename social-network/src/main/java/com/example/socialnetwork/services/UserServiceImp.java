package com.example.socialnetwork.services;

import com.example.socialnetwork.models.DTOs.ErrorDTO;
import com.example.socialnetwork.models.DTOs.UserDTO;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;

    public UserServiceImp (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> checkUserDetails(UserDTO userDTO, HttpServletRequest request){
        if(userDTO.getUsername() == null || userDTO.getEmail() == null ||userDTO
                .getPassword() == null) {
            return ResponseEntity.status(400).body(new ErrorDTO(request.getRequestURI(), "Please provide all user data!"));
        } else if (userRepository.existsUserByUsername(userDTO.getUsername())) {
            return ResponseEntity.status(400).body(new ErrorDTO(request.getRequestURI(), "Username is taken, please select another one!"));
        } else if (userRepository.existsUserByPassword(userDTO.getPassword())) {
            return ResponseEntity.status(400).body(new ErrorDTO(request.getRequestURI(), "Password is taken, please select another one!"));
        } else if (userRepository.existsUserByEmail(userDTO.getEmail())){
            return ResponseEntity.status(400).body(new ErrorDTO(request.getRequestURI(), "Email is taken, please select another one!"));
        }
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        userRepository.save(user);
        return ResponseEntity.status(200).body(userDTO);
    }
}
