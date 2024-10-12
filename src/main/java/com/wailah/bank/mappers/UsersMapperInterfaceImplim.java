package com.wailah.bank.mappers;

import com.wailah.bank.dtos.UsersRequestDto;
import com.wailah.bank.dtos.UsersResponseDto;
import com.wailah.bank.entities.Accounts;
import com.wailah.bank.entities.Roles;
import com.wailah.bank.entities.Users;
import com.wailah.bank.repositories.AccountsRepository;
import com.wailah.bank.repositories.RolesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class UsersMapperInterfaceImplim implements UsersMapperInterface {

    private final RolesRepository rolesRepository;
    private final AccountsRepository accountsRepository;

    public UsersMapperInterfaceImplim(RolesRepository rolesRepository, AccountsRepository accountsRepository) {
        this.rolesRepository = rolesRepository;
        this.accountsRepository = accountsRepository;
    }

    @Override
    public Users userRequestToUser(UsersRequestDto usersRequestDto) {
        Users user = new Users();
        BeanUtils.copyProperties(usersRequestDto,user);
        Collection<Roles> rolesList = turnIdsToRoles(usersRequestDto.getUserRolesIds());
        Collection<Accounts> accountsList =turnIdsToAccounts(usersRequestDto.getUserAccountsIds());
        user.setUserRoles(rolesList);
        user.setUserAccounts(accountsList);
        return user;
    }

    @Override
    public UsersResponseDto UserToUserResponse(Users users) {
        UsersResponseDto usersResponseDto = new UsersResponseDto();
        BeanUtils.copyProperties(users,usersResponseDto);
        return usersResponseDto;
    }

    public Collection<Roles> turnIdsToRoles(Collection<Integer> ids){

        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<Roles> rolesList = new LinkedList<>();
        for (int id:ids) {
            rolesList.add(rolesRepository.findById(id).get());
        }
        return rolesList;
    }

    public Collection<Accounts> turnIdsToAccounts(Collection<Integer> ids){

        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<Accounts> accountsList = new LinkedList<>();
        for (int id:ids) {
            accountsList.add(accountsRepository.findById(id).get());
        }
        return accountsList;
    }

}
