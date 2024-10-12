package com.wailah.bank.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Table(name = "ACCOUNTS")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "BALANCE")
    private double balance;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference // Child reference
    private Users user;

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        this.balance -= amount;
    }



}
