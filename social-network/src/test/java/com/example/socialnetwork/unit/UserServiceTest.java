package com.example.socialnetwork.unit;

import com.example.socialnetwork.exceptions.ResourceNotFoundException;
import com.example.socialnetwork.exceptions.UserDetailsTakenException;
import com.example.socialnetwork.models.DTOs.UserDTO;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.repositories.UserRepository;
import com.example.socialnetwork.services.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;



public class UserServiceTest {
    @InjectMocks
    private UserServiceImp userServiceImp;
    @Mock private UserRepository userRepository;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        user = new User("Agi", "pw", "email");
        userDTO = new UserDTO("Agi", "pw", "email");
    }

    @Test
    public void userRepositorySave(){
        when(userRepository.save(user)).thenReturn(user);

        Assertions.assertEquals(user, userRepository.save(user));
    }

    @Test
    public void checkUserDetailsOK (){
    Assertions.assertEquals(userDTO, userServiceImp.checkUserDetails(userDTO));
    }

    @Test
    public void checkUserDetailsUsernameMissing (){
        UserDTO userDTO1 = new UserDTO(null, "pw", "email");

        assertThrows(ResourceNotFoundException.class, () -> userServiceImp.checkUserDetails(userDTO1));
    }

    @Test
    public void checkUserDetailsPasswordMissing (){
        UserDTO userDTO1 = new UserDTO("username", null, "email");

        assertThrows(ResourceNotFoundException.class, () -> userServiceImp.checkUserDetails(userDTO1));
    }

    @Test
    public void checkUserDetailsEmailMissing (){
        UserDTO userDTO1 = new UserDTO("username", "pw", null);

        assertThrows(ResourceNotFoundException.class, () -> userServiceImp.checkUserDetails(userDTO1));
    }

    @Test
    public void checkUserDetailsUsernameTaken (){
        when(userRepository.existsUserByUsername(userDTO.getUsername())).thenReturn(true);

        assertThrows(UserDetailsTakenException.class, () -> userServiceImp.checkUserDetails(userDTO));
    }

    @Test
    public void checkUserDetailsPasswordTaken (){
        when(userRepository.existsUserByPassword(userDTO.getPassword())).thenReturn(true);

        assertThrows(UserDetailsTakenException.class, () -> userServiceImp.checkUserDetails(userDTO));
    }

    @Test
    public void checkUserDetailsEmailTaken (){
        when(userRepository.existsUserByEmail(userDTO.getEmail())).thenReturn(true);

        assertThrows(UserDetailsTakenException.class, () -> userServiceImp.checkUserDetails(userDTO));
    }
}
