package com.example.socialnetwork.security;

import com.example.socialnetwork.models.DTOs.UserDTO;
import com.example.socialnetwork.repositories.UserRepository;
import com.example.socialnetwork.security.jwt.JwtDTO;
import com.example.socialnetwork.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class LoginUtils {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    @Autowired
    public LoginUtils(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public JwtDTO login(UserDTO userDTO) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new JwtDTO(jwt);
    }
}
