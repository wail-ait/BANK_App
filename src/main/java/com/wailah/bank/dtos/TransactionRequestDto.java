package com.wailah.bank.dtos;

import com.wailah.bank.entities.TransactionType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class TransactionRequestDto {

    private TransactionType transactionType;

    private double amount;

    private int accountId;

    private int targetAccountId;

}
