package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.model.User;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;
@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users/getUserByEmail/{emailId}")
    public ResponseEntity<UserDto> getUserDetailsByEmail(@PathVariable String emailId){
        UserDto userDetails = userService.getUserDetailsByEmail(emailId);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> getUserDetails(@PathVariable String userId) {
        String desc = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(desc);
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody User user, @PathVariable String userId) {
        UserDto userDetails = userService.updateUserDetails(user, userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user){
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        UserDto userDetails = userService.createUserDetails(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetails);

    }
}
