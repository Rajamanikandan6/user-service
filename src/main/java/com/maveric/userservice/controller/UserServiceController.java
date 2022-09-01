package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.maveric.userservice.model.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserServiceController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user){
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        UserDto userDetails = userService.createUserDetails(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetails);
    }
}
