package com.maveric.userservice.service;

import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.EmailDuplicationException;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import com.maveric.userservice.constant.SuccessMessageConstant;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;

    public UserDto getUserDetails(String userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return modelDtoConverter.entityToDto(optionalUser);
    }
    public List<UserDto> getUsersDetails(int page, int pageSize) {
        Page<User> user = userRepository.findAll(PageRequest.of(page, pageSize));
        List<User> listUser = user.getContent();
        return modelDtoConverter.entityToDto(listUser);
    }

    public UserDto getUserDetailsByEmail(String emailId) {
        User optionalUser = userRepository.findByEmail(emailId).orElseThrow(() -> new UserNotFoundException(emailId));

        return modelDtoConverter.entityToDtoForEmail(optionalUser);
    }


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
        Optional<User> emailUser = userRepository.findByEmail(user.getEmail());
        if(!emailUser.isPresent()) {
            User newUser = userRepository.save(user);
            return modelDtoConverter.entityToDto(newUser);
        }else{
            throw new EmailDuplicationException(user.getEmail());
        }

    }

    public String deleteUser(String userId){
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
        return SuccessMessageConstant.SUCCESS_DELETE_USER;
    }
}
