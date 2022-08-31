package com.maveric.userservice.service;

import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.UserNotFoundException;
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
 class UserServiceTest {

    @Mock
    private UserRepository mockedUserRepository;

    @Mock
    private ModelDtoConverter modelDtoConverter;

    @InjectMocks
    private UserService userService;

    @Test
     void shouldReturnUserWhenUpdateUserInvoked() throws Exception {
        when(mockedUserRepository.findById("2c9cf08182f36d5a0182f3731f210")).thenReturn(Optional.ofNullable(getSampleUser()));
        when(modelDtoConverter.entityToDto(mockedUserRepository.save(getSampleUser()))).thenReturn(getSampleDtoUser());

        UserDto user = userService.updateUserDetails(getSampleUser(),"2c9cf08182f36d5a0182f3731f210");

        assertNotNull(user);
        assertSame(user.getEmail(),getSampleUser().getEmail());

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

    public UserDto getSampleDtoUser(){
        UserDto user = new UserDto();
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
