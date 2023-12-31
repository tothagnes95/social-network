package com.example.socialnetwork.services;

import com.example.socialnetwork.exceptions.ResourceNotFoundException;
import com.example.socialnetwork.exceptions.UserDetailsTakenException;
import com.example.socialnetwork.models.DTOs.UserDTO;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.repositories.UserRepository;
import com.example.socialnetwork.security.LoginUtils;
import com.example.socialnetwork.security.jwt.JwtDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;
    private LoginUtils loginUtils;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp (UserRepository userRepository, LoginUtils loginUtils, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.loginUtils = loginUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByUsername (String username){
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void checkUserDetailsNull (UserDTO userDTO) {
        if(userDTO.getUsername() == null || userDTO.getEmail() == null ||userDTO
                .getPassword() == null) {
            throw new ResourceNotFoundException("Password, username and email are required");
        }
    }

    public UserDTO checkUserDetails(UserDTO userDTO){
        checkUserDetailsNull(userDTO);
        System.out.println("register pw: " + userDTO.getPassword());
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        System.out.println("register encoded pw: " + encodedPassword);
        if (userRepository.existsUserByUsername(userDTO.getUsername())) {
            throw new UserDetailsTakenException("Username is taken, please select another one!");
        } else if (userRepository.existsUserByPassword(encodedPassword)) {
            throw new UserDetailsTakenException("Password is taken, please select another one!");
        } else if (userRepository.existsUserByEmail(userDTO.getEmail())){
            throw new UserDetailsTakenException("Email is taken, please select another one!");
        }

        User user = new User(userDTO.getUsername(), encodedPassword, userDTO.getEmail());
        userRepository.save(user);
        return userDTO;
    }


    public JwtDTO checkUserDetailsInDB(UserDTO userDTO) {
        checkUserDetailsNull(userDTO);
        return loginUtils.login(userDTO);
    }
}
