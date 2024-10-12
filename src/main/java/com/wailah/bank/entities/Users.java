package com.wailah.bank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity

@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Email(message = "please provide a valide email address")
    @Column(name = "EMAIL")
    private String email;

    @Pattern(regexp = "^0\\d{9}$", message = "phone number must start with a 0 and have exactly 10 digits")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "USER_ROLES")
    private Collection<Roles> userRoles = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference // Parent reference
    @JsonIgnore
    private Collection<Accounts> userAccounts;


}
