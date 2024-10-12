package com.wailah.bank.mappers;

import com.wailah.bank.dtos.AccountsRequestDto;
import com.wailah.bank.dtos.AccountsResponseDto;
import com.wailah.bank.entities.Accounts;

public interface AccountsMapperInterface {

    public Accounts accountRequestToAccount(AccountsRequestDto accountsRequestDto);
    public AccountsResponseDto accountToAccountResponse(Accounts accounts);

}
