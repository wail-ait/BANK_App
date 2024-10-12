package com.wailah.bank.services;

import com.wailah.bank.dtos.AccountsRequestDto;
import com.wailah.bank.dtos.AccountsResponseDto;

import java.util.List;

public interface AccountServicesInterface {

    //method to get all the accounts
    public List<AccountsResponseDto> getAllAccounts();

    //method to get an account by it's id
    public AccountsResponseDto getUserById(int id);

    //method to add new user
    public void addAccount(AccountsRequestDto accountsRequestDto);

    //method to update an existing account
    public void updateAccount(int id,AccountsRequestDto accountsRequestDto);

    //method to delete an existing account
    public void deleteAccount(int id);

}
