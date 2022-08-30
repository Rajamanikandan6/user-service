package com.maveric.userservice.service;

import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserDetails(int userId){
        Optional<User> optionalUser=userRepository.findById(userId);
        optionalUser.orElseThrow(()-> new UserNotFoundException(userId));
        return optionalUser;
    }
}
