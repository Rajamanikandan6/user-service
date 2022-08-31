package com.maveric.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maveric.userservice.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

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

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
