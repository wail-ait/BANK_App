package com.wailah.bank.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Table(name = "TRANSACTIONS")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer Id;

    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "ACCOUNT_ID")
    private int accountId;

    @Column(name = "TARGET_ACCOUNT_ID")
    private int targetAccountId;

}
