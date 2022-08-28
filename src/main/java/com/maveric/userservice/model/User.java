package com.maveric.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

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

    private String gender;

    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
