package com.maveric.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maveric.userservice.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "firstname shouldn't be empty")
    private String firstName;

    private String middleName;

    @NotNull(message = "lastname shouldn't be empty")
    private String lastName;

    @Email
    private String email;

    @NotNull(message = "phoneNumber shouldn't be empty")
    private String phoneNumber;

    private String address;

    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
