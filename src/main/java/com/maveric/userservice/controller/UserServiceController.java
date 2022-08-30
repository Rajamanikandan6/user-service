package com.maveric.userservice.controller;

import com.maveric.userservice.model.User;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    @Autowired
    UserService userService;

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable int userId){
        User userDetails = userService.updateUserDetails(user,userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }
}
