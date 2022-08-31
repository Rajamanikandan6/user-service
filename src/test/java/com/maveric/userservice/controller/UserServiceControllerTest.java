package com.maveric.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.TransactionSystemException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest(UserServiceController.class)
@Tag("Integration tests")
 class UserServiceControllerTest {

    private static final String API_V1_USERS = "/api/v1/users";

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
     void shouldUpdateUserWhenRequestMadeToUpdateUser() throws Exception{
        mvc.perform(put(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f210000").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleUser())))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
     void shouldThrowBadRequestWhenUserDetailsAreWrong() throws Exception{
        User user = new User();
        user.setFirstName(null);
        user.setLastName("s");
        user.setEmail("raja@gmail.com");
        user.setPassword("12345");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("pollachi");
        user.setPhoneNumber("9965571147");
        mvc.perform(put(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f210000").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
     void shouldReturnInternalServerWhenDbReturnsError() throws Exception{
        when(userService.updateUserDetails(Mockito.any(User.class),eq("2c9cf08182f36d5a0182f3731f210000"))).thenThrow(new IllegalArgumentException());
        mvc.perform(put(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f210000").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleUser())))
                .andExpect(status().isInternalServerError())
                .andDo(print());

    }

    public User getSampleUser(){
        User user = new User();
        user.setFirstName("raja");
        user.setLastName("s");
        user.setEmail("raja@gmail.com");
        user.setPassword("12345");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("kinathukadavu");
        user.setPhoneNumber("9965571147");
        return user;
    }
}
