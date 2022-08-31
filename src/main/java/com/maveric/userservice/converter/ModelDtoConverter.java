package com.maveric.userservice.converter;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDtoConverter {

    public List<UserDto> entityToDto(List<User> user){
        List<UserDto> userDto = new ArrayList<UserDto>();
        user.stream().forEach(u -> {
            UserDto singleUser = new UserDto();
            singleUser.setFirstName(u.getFirstName());
            singleUser.setMiddleName(u.getMiddleName());
            singleUser.setLastName(u.getLastName());
            singleUser.setEmail(u.getEmail());
            singleUser.setPhoneNumber(u.getPhoneNumber());
            singleUser.setAddress(u.getAddress());
            singleUser.setGender(u.getGender());
            singleUser.setRole(u.getRole());
            singleUser.setDateOfBirth(u.getDateOfBirth());
            userDto.add(singleUser);
        });


        return userDto;

    }
}
