package com.maveric.userservice.service;

import com.maveric.userservice.constant.SuccessMessageConstant;
import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
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

    @Autowired
    ModelDtoConverter modelDtoConverter;


    public UserDto updateUserDetails(User user, String userId){
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

            return modelDtoConverter.entityToDto(userRepository.save(newUser));
        }else{
            throw new UserNotFoundException(userId);
        }
    }

    public UserDto createUserDetails(User user){
        User newUser =userRepository.save(user);
        return modelDtoConverter.entityToDto(newUser);
    }

    public String deleteUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
        return SuccessMessageConstant.SUCCESS_DELETE_USER;
    }
}
