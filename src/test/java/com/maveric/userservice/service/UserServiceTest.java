package com.maveric.userservice.service;

import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockedUserRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWhenGetUserInvoked() throws Exception {
        when(mockedUserRepository.findAll()).thenReturn(getSampleUserForGetUsers());

        List<User> user = userService.getUsersDetails(0,10);

        assertNotNull(user);

    }

    public List<User> getSampleUserForGetUsers(){
        List<User> userList = new ArrayList<User>();
        User user = new User();
        User sampleUser = new User();
        user.setFirstName("raja");
        user.setLastName("s");
        user.setEmail("shreeharsha@gmail.com");
        user.setPassword("12345");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("pollachi");
        user.setPhoneNumber("9965571147");
        sampleUser.setFirstName("ram");
        sampleUser.setLastName("s");
        sampleUser.setEmail("shreeharsha1@gmail.com");
        sampleUser.setPassword("13456");
        sampleUser.setGender(Gender.MALE);
        sampleUser.setDateOfBirth("2021-02-02");
        sampleUser.setAddress("pollachi");
        sampleUser.setPhoneNumber("9965571147");

        userList.add(user);
        userList.add(sampleUser);

        return userList;
    }
}
