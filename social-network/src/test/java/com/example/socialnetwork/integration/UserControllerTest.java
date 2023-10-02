package com.example.socialnetwork.integration;

import com.example.socialnetwork.controllers.UserController;
import com.example.socialnetwork.exceptions.ErrorMessage;
import com.example.socialnetwork.models.DTOs.UserDTO;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.repositories.UserRepository;
import com.example.socialnetwork.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();
    private UserDTO userDTO;
    private User user;
    @Mock
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        userDTO = new UserDTO("agi", "pw", "email");
        user = new User("agi", "pw", "email");
    }

    @Test
    public void registrationOkTest () throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDTO)))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(userDTO)));
    }

    @Test
    public void registrationUsernameMissing () throws Exception {
        UserDTO userDTO1 = new UserDTO(null, "pw", "email");
        ErrorMessage errorMessage = new ErrorMessage("uri=/register", "Password, username and email are required");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userDTO1)))
                .andExpect(status().is(400))
                .andExpect(content().json(mapper.writeValueAsString(errorMessage)));
    }

    @Test
    public void registrationUsernameTaken () throws Exception {
        userRepository.save(user);
        ErrorMessage errorMessage = new ErrorMessage("uri=/register", "Username is taken, please select another one!");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userDTO)))
                .andExpect(status().is(409))
                .andExpect(content().json(mapper.writeValueAsString(errorMessage)));
    }

}
