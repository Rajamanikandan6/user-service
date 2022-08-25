package com.maveric.userservice.service;

import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserDetails(User user,int userId){
        Optional<User> userFromDb = userRepository.findById(userId);
        if(userFromDb.isPresent()) {
            User newUser = userFromDb.get();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setMiddleName(user.getMiddleName());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setEmail(user.getEmail());
            newUser.setAddress(user.getAddress());
            newUser.setDateOfBirth(user.getDateOfBirth());
            newUser.setGender(user.getGender());

            return userRepository.save(newUser);
        }
        return null;
    }
}
