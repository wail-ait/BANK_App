package com.wailah.bank.dtos;

import com.wailah.bank.entities.State;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class AccountsRequestDto {

    private double balance;

    @Enumerated(EnumType.STRING)
    private State state;

    private Integer idUser;

}
