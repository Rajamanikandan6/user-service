package com.maveric.userservice.service;

import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String deleteUser(int userId){
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }
}
