package com.maveric.userservice.controller;

import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(UserServiceController.class)
@Tag("Integration tests")
public class UserServiceControllerTest {
    private static final String API_V1_USERS = "/api/v1/users";

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldGetUserWhenRequestMadeToGetUser() throws Exception{
        mvc.perform(get(API_V1_USERS))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void shouldReturnInternalServerWhenDbReturnsError() throws Exception{
        when(userService.getUsersDetails(0,10)).thenThrow(new IllegalArgumentException());
        mvc.perform(get(API_V1_USERS))
                .andExpect(status().isInternalServerError())
                .andDo(print());

    }
}
