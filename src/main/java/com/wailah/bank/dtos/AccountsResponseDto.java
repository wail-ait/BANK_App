package com.wailah.bank.dtos;

import com.wailah.bank.entities.State;
import com.wailah.bank.entities.Users;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AccountsResponseDto {

    private Integer id;

    private double balance;

    @Enumerated(EnumType.STRING)
    private State state;

    private Users user;

}
