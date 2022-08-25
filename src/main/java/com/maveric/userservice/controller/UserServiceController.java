package com.maveric.userservice.controller;

import com.maveric.userservice.model.User;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserServiceController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam int page , @RequestParam int pageSize){
        List<User> usersDetails = userService.getUsersDetails(page,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(usersDetails);
    }
}
