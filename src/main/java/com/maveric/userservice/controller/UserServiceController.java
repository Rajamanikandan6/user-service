package com.maveric.userservice.controller;

import com.maveric.userservice.model.User;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    @Autowired
    UserService userService;

    @GetMapping("/users/getUserByEmail/{emailId}")
    public ResponseEntity<Optional<User>> getUserDetailsByEmail(@PathVariable String emailId){
        Optional<User> userDetails = userService.getUserDetailsByEmail(emailId);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }
}
