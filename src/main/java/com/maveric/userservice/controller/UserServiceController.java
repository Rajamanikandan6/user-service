package com.maveric.userservice.controller;

import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    @Autowired
    UserService userService;

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> getUserDetails(@PathVariable int userId){
        String desc = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(desc);
    }
}
