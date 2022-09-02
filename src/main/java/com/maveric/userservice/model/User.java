package com.maveric.userservice.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maveric.userservice.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "firstname shouldn't be empty")
    private String firstName;

    private String middleName;

    @NotBlank(message = "lastname shouldn't be empty")
    private String lastName;

    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Size(min = 10,max = 10)
    @NotBlank(message = "phoneNumber shouldn't be empty")
    private String phoneNumber;

    @NotBlank
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

