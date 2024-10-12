package com.wailah.bank.web;

import com.wailah.bank.dtos.AccountsRequestDto;
import com.wailah.bank.dtos.AccountsResponseDto;
import com.wailah.bank.services.AccountServicesInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsRestController {

    private final AccountServicesInterface accountServicesInterface;

    public AccountsRestController(AccountServicesInterface accountServicesInterface) {
        this.accountServicesInterface = accountServicesInterface;
    }

    @GetMapping("/account")
    public List<AccountsResponseDto> getAll(){
        return accountServicesInterface.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    public AccountsResponseDto getById(@PathVariable int id){
        return accountServicesInterface.getUserById(id);
    }

    @PostMapping("/account")
    public void add(@RequestBody AccountsRequestDto accountsRequestDto){
        accountServicesInterface.addAccount(accountsRequestDto);
    }

    @PutMapping("/account/{id}")
    public void update(@PathVariable int id,@RequestBody AccountsRequestDto accountsRequestDto){
        accountServicesInterface.updateAccount(id,accountsRequestDto);
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable int id){
        accountServicesInterface.deleteAccount(id);
    }
}
