package com.wailah.bank.services;

import com.wailah.bank.dtos.UsersRequestDto;
import com.wailah.bank.dtos.UsersResponseDto;

import java.util.List;

public interface UsersServiceInterface {

    //method to get all the users
    public List<UsersResponseDto> getAllUsers();

    //method to get user by it's id
    public UsersResponseDto getById(int id);

    //method to add new user
    public void addUser(UsersRequestDto usersRequestDto);

    //method to update a user
    public void updateUser(int id,UsersRequestDto usersRequestDto);

    //method to delete a user
    public void deleteUser(int id);

}
