package com.maveric.userservice.converter;

import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.dto.Error;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.dto.UserEmailDto;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ModelToDtoTest {

    @InjectMocks
    private ModelDtoConverter modelDtoConverter;

    @Test
    void handleEntityToDto() {
        User user =getSampleUser();
        UserDto userDto = modelDtoConverter.entityToDto(user);
        assertNotNull(userDto.getEmail());
    }

    @Test
    void handleDtoToEntity() {
        UserDto userDto =getSampleDtoUser();
        User user = modelDtoConverter.dtoToEntity(userDto);
        assertNotNull(user.getEmail());
    }

    @Test
    void handleEntityToDtoEmail() {
        User user =getSampleUser();
        UserEmailDto userEmailDto = modelDtoConverter.entityToDtoForEmail(user);
        assertNotNull(userEmailDto.getEmail());
    }

    @Test
    void shouldReturnErrorWhenLastnameIsEmptyEntityToDtoEmail() {
        User user =getSampleUser();
        UserDto userDto = modelDtoConverter.entityToDto(user);
        assertNull(userDto.getPassword());
    }

    public User getSampleUser(){
        User user = new User();
        user.setFirstName("raja");
        user.setEmail("shreeharsha06@gmail.com");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("pollachi");
        user.setPhoneNumber("9965571147");

        return user;
    }

    public UserDto getSampleDtoUser(){
        UserDto user = new UserDto();
        user.setFirstName("raja");
        user.setEmail("shreeharsha06@gmail.com");
        user.setPassword("12345");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("pollachi");
        user.setPhoneNumber("9965571147");
        return user;
    }
}
