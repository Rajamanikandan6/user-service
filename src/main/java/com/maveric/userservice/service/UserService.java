package com.maveric.userservice.service;

import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;

    public List<UserDto> getUsersDetails(int page, int pageSize){
        Page<User> user = userRepository.findAll(PageRequest.of(page,pageSize));
        List<User> listUser = user.getContent();
        return modelDtoConverter.entityToDto(listUser);
    }
}
