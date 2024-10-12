package com.wailah.bank.services;

import com.wailah.bank.dtos.AccountsRequestDto;
import com.wailah.bank.dtos.AccountsResponseDto;
import com.wailah.bank.entities.Accounts;
import com.wailah.bank.entities.Users;
import com.wailah.bank.mappers.AccountsMapperInterface;
import com.wailah.bank.repositories.AccountsRepository;
import com.wailah.bank.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServicesInterfaceImplim implements AccountServicesInterface {

    private final AccountsRepository accountsRepository;
    private final AccountsMapperInterface accountsMapperInterface;
    private final UsersRepository usersRepository;

    public AccountServicesInterfaceImplim(AccountsRepository accountsRepository, AccountsMapperInterface accountsMapperInterface, UsersRepository usersRepository) {
        this.accountsRepository = accountsRepository;
        this.accountsMapperInterface = accountsMapperInterface;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<AccountsResponseDto> getAllAccounts() {
        List<Accounts> accountsList = accountsRepository.findAll();
        List<AccountsResponseDto> accountsResponseList = new ArrayList<>();
        for (Accounts account : accountsList){
            accountsResponseList.add(accountsMapperInterface.accountToAccountResponse(account));
        }
        return accountsResponseList;
    }

    @Override
    public AccountsResponseDto getUserById(int id) {
        Accounts account = accountsRepository.findById(id).get();
        AccountsResponseDto accountsResponseDto = accountsMapperInterface.accountToAccountResponse(account);
        return accountsResponseDto;
    }

    @Override
    public void addAccount(AccountsRequestDto accountsRequestDto) {
        Accounts account = accountsMapperInterface.accountRequestToAccount(accountsRequestDto);
        accountsRepository.save(account);
    }

    @Override
    public void updateAccount(int id, AccountsRequestDto accountsRequestDto) {
        Accounts account = accountsRepository.findById(id).get();
        if (accountsRequestDto.getBalance()>0)
            account.setBalance(accountsRequestDto.getBalance());
        if (accountsRequestDto.getState()!=null)
            account.setState(accountsRequestDto.getState());
        if (accountsRequestDto.getIdUser()!=null)
            account.setUser(usersRepository.findById(accountsRequestDto.getIdUser()).get());

        accountsRepository.save(account);
    }

    @Override
    public void deleteAccount(int id) {
        accountsRepository.deleteById(id);
    }
}
