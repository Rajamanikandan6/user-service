package com.maveric.userservice.controller;

import com.maveric.userservice.constant.ErrorMessageConstants;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.UserIdMismatch;
import com.maveric.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;

import static com.maveric.userservice.constant.ErrorMessageConstants.USER_ID_MISMATCH;

@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable String userId,@RequestHeader(value = "userId") String headerUserId){
        if(userId.equals(headerUserId)) {
            UserDto userDetails = userService.getUserDetails(userId);
            logger.info("User found {}", userId);
            return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        }else{
            logger.info("User id mismatch");
            throw new UserIdMismatch(USER_ID_MISMATCH);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam int page , @RequestParam int pageSize) {
        List<UserDto> usersDetails = userService.getUsersDetails(page, pageSize);
        logger.info("Fetched all users");
        return ResponseEntity.status(HttpStatus.OK).body(usersDetails);
    }

    @GetMapping("/users/getUserByEmail/{emailId}")
    public ResponseEntity<UserDto> getUserDetailsByEmail(@PathVariable String emailId){
        UserDto userDetails = userService.getUserDetailsByEmail(emailId);
        logger.info("User found by Email {}" ,emailId);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUserDetails(@PathVariable String userId,@RequestHeader(value = "userId") String headerUserId) {
        if(userId.equals(headerUserId)) {
            String desc = userService.deleteUser(userId);
            logger.info("User deleted {}", userId);
            return ResponseEntity.status(HttpStatus.OK).body(desc);
        }else{
            throw new UserIdMismatch(USER_ID_MISMATCH);
        }
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId,@RequestHeader(value = "userId") String headerUserId) {
        logger.info("User updated {}", headerUserId);
        if(headerUserId.equals(userId) && userId.equals(userDto.getId())) {
            UserDto userDetails = userService.updateUserDetails(userDto, userId);
            logger.info("User updated {}", userId);
            return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        }else{
            throw new UserIdMismatch(USER_ID_MISMATCH);
        }
    }
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        userDto.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));
        UserDto userDetails = userService.createUserDetails(userDto);
        logger.info("User created {}" ,userDetails.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetails);
    }
}
