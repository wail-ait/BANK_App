package com.wailah.bank;

import com.wailah.bank.entities.*;
import com.wailah.bank.repositories.AccountsRepository;
import com.wailah.bank.repositories.RolesRepository;
import com.wailah.bank.repositories.TransactionsRepository;
import com.wailah.bank.repositories.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UsersRepository usersRepository, AccountsRepository accountsRepository, RolesRepository rolesRepository, TransactionsRepository transactionsRepository) {
        return args -> {

            // ROLES :
            Roles userRole = Roles.builder()
                    .roleName("USER")
                    .build();
            Roles adminRole = Roles.builder()
                    .roleName("ADMIN")
                    .build();

            // Save roles first to avoid foreign key constraints later
            rolesRepository.save(userRole);
            rolesRepository.save(adminRole);

            // USERS :
            Users user1 = Users.builder()
                    .firstName("wail")
                    .lastName("AH")
                    .email("wail_AH@gmail.com")
                    .phoneNumber("0653939159")
                    .dateOfBirth(LocalDate.of(2002, 2, 2))
                    .userRoles(Collections.singleton(adminRole))  // assign role to user
                    .userAccounts(null)
                    .build();
            Users user2 = Users.builder()
                    .firstName("loay")
                    .lastName("AH")
                    .email("loay_AH@gmail.com")
                    .phoneNumber("0699999999")
                    .dateOfBirth(LocalDate.of(2020, 12, 20))
                    .userRoles(Collections.singleton(userRole))
                    .userAccounts(null)
                    .build();
            Users user3 = Users.builder()
                    .firstName("jamal")
                    .lastName("AH")
                    .email("jamal_AH@gmail.com")
                    .phoneNumber("0688888888")
                    .dateOfBirth(LocalDate.of(1990, 9, 19))
                    .userRoles(Collections.singleton(userRole))
                    .userAccounts(null)
                    .build();

            // ACCOUNTS :
            Accounts account1 = Accounts.builder()
                    .balance(30000.0)  // Use decimal format
                    .state(State.Active)
                    .user(user1)
                    .build();
            Accounts account2 = Accounts.builder()
                    .balance(5000.0)
                    .state(State.Suspended)
                    .user(user2)
                    .build();
            Accounts account3 = Accounts.builder()
                    .balance(500000.0)
                    .state(State.Active)
                    .user(user3)
                    .build();

            // Update user accounts
            user1.setUserAccounts(Collections.singleton(account1));
            user2.setUserAccounts(Collections.singleton(account2));
            user3.setUserAccounts(Collections.singleton(account3));

            Transactions transaction1 = Transactions.builder()
                    .transactionType(TransactionType.Transfer)
                    .amount(400)
                    .accountId(1)
                    .targetAccountId(2)
                    .build();

            // Save users before setting accounts
            usersRepository.save(user1);
            usersRepository.save(user2);
            usersRepository.save(user3);

            // Save accounts
            accountsRepository.save(account1);
            accountsRepository.save(account2);
            accountsRepository.save(account3);



            // Save updated users
            usersRepository.save(user1);
            usersRepository.save(user2);
            usersRepository.save(user3);

            // Save the transactions
            transactionsRepository.save(transaction1);

        };
    }
}
