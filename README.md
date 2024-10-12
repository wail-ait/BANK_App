# Bank Application

## Overview

This is a Spring Boot-based monolithic application designed to manage bank accounts, users, roles, and transactions.
The application provides RESTful APIs to facilitate the management of banking operations and integrates various components
such as data transfer objects (DTOs), entities, repositories, mappers, and services.

## Features

- Manage bank accounts, users, roles, and transactions.
- RESTful API endpoints for all functionalities.
- Data transfer objects for safe data handling.
- Database integration for persistent data storage.

## Technologies Used

- **Java**: Version 11 or higher
- **Spring Boot**: Version 2.x
- **Spring Data JPA**: For database interactions
- **Hibernate**: ORM framework for database management
- **Maven**: Build tool
- **MySQL**: Database for persistent data storage
- **Apache Commons**: For various utility functions

## Project Structure

```plaintext
src
└── main
    ├── java
    │   └── com
    │       └── wailah
    │           └── bank
    │               ├── BankApplication.java
    │               ├── dtos
    │               │   ├── AccountsRequestDto.java
    │               │   ├── AccountsResponseDto.java
    │               │   ├── RolesRequestDto.java
    │               │   ├── RolesResponseDto.java
    │               │   ├── TransactionRequestDto.java
    │               │   ├── TransactionResponseDto.java
    │               │   ├── UsersRequestDto.java
    │               │   └── UsersResponseDto.java
    │               ├── entities
    │               │   ├── Accounts.java
    │               │   ├── Roles.java
    │               │   ├── State.java
    │               │   ├── TransactionType.java
    │               │   ├── Transactions.java
    │               │   └── Users.java
    │               ├── mappers
    │               │   ├── AccountsMapperInterface.java
    │               │   ├── AccountsMapperInterfaceImplim.java
    │               │   ├── RolesMapperInterface.java
    │               │   ├── RolesMapperInterfaceImplim.java
    │               │   ├── TransactionalMapperInterface.java
    │               │   ├── TransactionalMapperInterfaceImplim.java
    │               │   ├── UsersMapperInterface.java
    │               │   └── UsersMapperInterfaceImplim.java
    │               ├── repositories
    │               │   ├── AccountsRepository.java
    │               │   ├── RolesRepository.java
    │               │   ├── TransactionsRepository.java
    │               │   └── UsersRepository.java
    │               ├── services
    │               │   ├── AccountServicesInterface.java
    │               │   ├── AccountServicesInterfaceImplim.java
    │               │   ├── RolesServiceInterface.java
    │               │   ├── RolesServiceInterfaceImpl.java
    │               │   ├── TransactionsServiceInterface.java
    │               │   ├── TransactionsServiceInterfaceImplim.java
    │               │   └── UsersServiceInterface.java
    │               │   └── UsersServiceInterfaceImplim.java
    │               └── web
    │                   ├── AccountsRestController.java
    │                   ├── RolesRestController.java
    │                   ├── TransactionsRestController.java
    │                   └── UsersRestController.java
    └── resources
        └── application.properties
````

## Installation

-Clone the Repository:
    
    ```bash
    git clone https://github.com/wail-ait/BANK_App.git
    ```

-Build the Project: Ensure you have Maven installed:

    ```bash
    mvn clean install
    ```

-Run the Application:

    ```bash
    mvn spring-boot:run
    ```

## Access the API

-The application runs on:

    **http://localhost:8081**

-API Endpoints:

### Accounts:
- **GET** `/accounts` : Retrieve all accounts
- **GET** `/accounts/{id}` : Retrieve an account by ID
- **POST** `/accounts` : Create a new account
- **PUT** `/accounts/{id}` : Update an existing account
- **DELETE** `/accounts/{id}` : Delete an existing account

### Users:
- **GET** `/users` : Retrieve all users
- **GET** `/users/{id}` : Retrieve a user by ID
- **POST** `/users` : Create a new user
- **PUT** `/users/{id}` : Update an existing user
- **DELETE** `/users/{id}` : Delete an existing user

### Roles:
- **GET** `/roles` : Retrieve all roles
- **GET** `/roles/{id}` : Retrieve a role by ID
- **POST** `/roles` : Create a new role
- **PUT** `/roles/{id}` : Update an existing role
- **DELETE** `/roles/{id}` : Delete an existing role

### Transactions:
- **GET** `/transactions` : Retrieve all transactions
- **GET** `/transactions/{id}` : Retrieve a transaction by ID
- **POST** `/transactions` : Create a new transaction
- **PUT** `/transactions/{id}` : Update an existing transaction
- **DELETE** `/transactions/{id}` : Delete an existing transaction
