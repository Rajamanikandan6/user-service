package com.maveric.userservice.service;

import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final String TEST_USER_ID = "12345678";

    @Mock
    private UserRepository mockedUserRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUserWhenGetUserInvoked() throws Exception {
        when(mockedUserRepository.findById(1)).thenReturn(Optional.of(getSampleUser()));

        Optional<User> user = userService.getUserDetails(1);

        assertNotNull(user);
        assertSame(user.get().getEmail(),getSampleUser().getEmail());

    }

    public User getSampleUser(){
        User user = new User();
        user.setFirstName("raja");
        user.setLastName("s");
        user.setEmail("shreeharsha@gmail.com");
        user.setPassword("12345");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("pollachi");
        user.setPhoneNumber("9965571147");
        return user;
    }
}
