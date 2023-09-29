package com.example.socialnetwork.services;

import com.example.socialnetwork.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;

    public UserServiceImp (UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
