package com.maveric.userservice.service;

import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;

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
    void shouldReturnUserWhenGetUsersInvoked() throws Exception {
        when(mockedUserRepository.findAll(PageRequest.of(0,10))).thenReturn(Page.empty());
        when(modelDtoConverter.entityToDto(getSampleUsers())).thenReturn(getSampleUserForGetUsers());


        List<UserDto> user = userService.getUsersDetails(0,10);

        assertNotNull(user);

    }

    @Test
    void shouldReturnUserWhenGetUserByEmailInvoked() throws Exception {
        when(mockedUserRepository.findByEmail("shreeharsha06@gmail.com")).thenReturn(Optional.of(getSampleUser()));
        when(modelDtoConverter.entityToDto(any(User.class))).thenReturn(getSampleDtoUser());

        UserDto user = userService.getUserDetailsByEmail("shreeharsha06@gmail.com");

        assertNotNull(user);
        assertSame(user.getEmail(),getSampleUser().getEmail());
    }

    @Test
    void shouldReturnUserWhenGetUserInvoked() throws Exception {
        when(mockedUserRepository.findById("2c9cf08182f36d5a0182f3731f210000")).thenReturn(Optional.of(getSampleUser()));

        String message = userService.deleteUser("2c9cf08182f36d5a0182f3731f210000");
        assertNotNull(message);
        assertSame(message, "User Deleted Successfully");
    }

    @Test
     void shouldReturnUserWhenUpdateUserInvoked() throws Exception {
        when(mockedUserRepository.findById("2c9cf08182f36d5a0182f3731f210")).thenReturn(Optional.ofNullable(getSampleUser()));
        when(modelDtoConverter.entityToDto(mockedUserRepository.save(getSampleUser()))).thenReturn(getSampleDtoUser());

        UserDto user = userService.updateUserDetails(getSampleUser(), "2c9cf08182f36d5a0182f3731f210");

        assertNotNull(user);
        assertSame(user.getEmail(),getSampleUser().getEmail());
    }

    @Test
     void shouldReturnUserWhenCreateUserInvoked() throws Exception {
        when(mockedUserRepository.save(any(User.class))).thenReturn(getSampleUser());
        when(modelDtoConverter.entityToDto(any(User.class))).thenReturn(getSampleDtoUser());

        UserDto user = userService.createUserDetails(getSampleUser());

        assertNotNull(user);
        assertSame(user.getEmail(),getSampleUser().getEmail());

    }

    public User getSampleUser(){
        User user = new User();
        user.setFirstName("raja");
        user.setLastName("s");
        user.setEmail("shreeharsha06@gmail.com");
        user.setPassword("12345");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("pollachi");
        user.setPhoneNumber("9965571147");

        return user;
    }

    public List<User> getSampleUsers(){
        List<User> userList = new ArrayList<User>();
        User user = new User();
        User sampleUser = new User();
        user.setFirstName("raja");
        user.setLastName("s");
        user.setEmail("shreeharsha@gmail.com");
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

    public UserDto getSampleDtoUser(){
        UserDto user = new UserDto();
        user.setFirstName("raja");
        user.setLastName("s");
        user.setEmail("shreeharsha06@gmail.com");
        user.setPassword("12345");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("pollachi");
        user.setPhoneNumber("9965571147");
        return user;
    }
    public List<UserDto> getSampleUserForGetUsers(){
        List<UserDto> userList = new ArrayList<UserDto>();
        UserDto user = new UserDto();
        UserDto sampleUser = new UserDto();
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
