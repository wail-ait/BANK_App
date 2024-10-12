package com.wailah.bank.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class UsersRequestDto {

    private String firstName;

    private String lastName;

    @Email(message = "please provide a valide email address")
    private String email;

    @Pattern(regexp = "^0\\d{9}$", message = "phone number must start with a 0 and have exactly 10 digits")
    private String phoneNumber;

    private LocalDate dateOfBirth;

    private Collection<Integer> userRolesIds = new LinkedList<>();

    private Collection<Integer> userAccountsIds;

}
