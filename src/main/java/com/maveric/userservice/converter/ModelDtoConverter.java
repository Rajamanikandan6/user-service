package com.maveric.userservice.converter;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.model.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDtoConverter {

    public List<UserDto> entityToDto(List<User> user) {
        List<UserDto> userDto = new ArrayList<>();
        user.stream().forEach(fetchUser -> {
            UserDto singleUser = new UserDto();
            singleUser.setId(fetchUser.getId());
            singleUser.setFirstName(fetchUser.getFirstName());
            singleUser.setMiddleName(fetchUser.getMiddleName());
            singleUser.setLastName(fetchUser.getLastName());
            singleUser.setEmail(fetchUser.getEmail());
            singleUser.setPhoneNumber(fetchUser.getPhoneNumber());
            singleUser.setAddress(fetchUser.getAddress());
            singleUser.setGender(fetchUser.getGender());
            singleUser.setRole(fetchUser.getRole());
            singleUser.setDateOfBirth(fetchUser.getDateOfBirth());
            userDto.add(singleUser);
        });
        return userDto;
    }

    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setGender(user.getGender());
        userDto.setRole(user.getRole());
        userDto.setDateOfBirth(user.getDateOfBirth());

        return userDto;

    }

    public UserDto entityToDtoForEmail(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setGender(user.getGender());
        userDto.setRole(user.getRole());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setPassword(user.getPassword());

        return userDto;

    }
}
