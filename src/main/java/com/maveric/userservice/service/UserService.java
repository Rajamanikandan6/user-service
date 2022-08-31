package com.maveric.userservice.service;

import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;

    public UserDto getUserDetailsByEmail(String emailId){
        User optionalUser = userRepository.findByEmail(emailId).orElseThrow(()-> new UserNotFoundException(emailId));

        return modelDtoConverter.entityToDto(optionalUser);
    }
}
