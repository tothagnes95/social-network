package com.example.socialnetwork.services;

import com.example.socialnetwork.exceptions.ResourceNotFoundException;
import com.example.socialnetwork.exceptions.UserDetailsTakenException;
import com.example.socialnetwork.models.DTOs.UserDTO;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;

    public UserServiceImp (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO checkUserDetails(UserDTO userDTO){
        if(userDTO.getUsername() == null || userDTO.getEmail() == null ||userDTO
                .getPassword() == null) {
            throw new ResourceNotFoundException("Password, username and email are required");
        } else if (userRepository.existsUserByUsername(userDTO.getUsername())) {
            throw new UserDetailsTakenException("Username is taken, please select another one!");
        } else if (userRepository.existsUserByPassword(userDTO.getPassword())) {
            throw new UserDetailsTakenException("Password is taken, please select another one!");
        } else if (userRepository.existsUserByEmail(userDTO.getEmail())){
            throw new UserDetailsTakenException("Email is taken, please select another one!");
        }
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        userRepository.save(user);
        return userDTO;
    }
}
