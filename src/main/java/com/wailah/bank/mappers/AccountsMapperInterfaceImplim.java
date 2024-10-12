package com.wailah.bank.mappers;

import com.wailah.bank.dtos.AccountsRequestDto;
import com.wailah.bank.dtos.AccountsResponseDto;
import com.wailah.bank.entities.Accounts;
import com.wailah.bank.entities.Users;
import com.wailah.bank.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountsMapperInterfaceImplim implements AccountsMapperInterface {

    private final UsersRepository usersRepository;

    public AccountsMapperInterfaceImplim(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Accounts accountRequestToAccount(AccountsRequestDto accountsRequestDto) {
        Accounts account = new Accounts();
        BeanUtils.copyProperties(accountsRequestDto,account);
        Users user = usersRepository.findById(accountsRequestDto.getIdUser()).get();
        account.setUser(user);
        return account;
    }

    @Override
    public AccountsResponseDto accountToAccountResponse(Accounts accounts) {
        AccountsResponseDto accountsResponseDto = new AccountsResponseDto();
        BeanUtils.copyProperties(accounts,accountsResponseDto);
        return accountsResponseDto;
    }
}
