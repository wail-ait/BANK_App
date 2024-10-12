package com.wailah.bank.services;

import com.wailah.bank.dtos.UsersRequestDto;
import com.wailah.bank.dtos.UsersResponseDto;
import com.wailah.bank.entities.Accounts;
import com.wailah.bank.entities.State;
import com.wailah.bank.entities.Users;
import com.wailah.bank.mappers.UsersMapperInterface;
import com.wailah.bank.mappers.UsersMapperInterfaceImplim;
import com.wailah.bank.repositories.AccountsRepository;
import com.wailah.bank.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsersServiceInterfaceImplim implements UsersServiceInterface {

    private final UsersRepository usersRepository;
    private final AccountsRepository accountsRepository;
    private final UsersMapperInterface usersMapperInterface;

    private UsersMapperInterfaceImplim usersMapperInterfaceImplim;

    public UsersServiceInterfaceImplim(UsersRepository usersRepository, AccountsRepository accountsRepository, UsersMapperInterface usersMapperInterface, UsersMapperInterfaceImplim usersMapperInterfaceImplim) {
        this.usersRepository = usersRepository;
        this.accountsRepository = accountsRepository;
        this.usersMapperInterface = usersMapperInterface;
        this.usersMapperInterfaceImplim = usersMapperInterfaceImplim;
    }
    @Override
    public List<UsersResponseDto> getAllUsers() {
        List<UsersResponseDto> usersResponseList = new ArrayList<>();
        List<Users> usersList = usersRepository.findAll();
        for (Users users: usersList) {
            usersResponseList.add(usersMapperInterface.UserToUserResponse(users));
        }
        return usersResponseList;
    }

    @Override
    public UsersResponseDto getById(int id) {
        Users user = usersRepository.findById(id).get();
        return usersMapperInterface.UserToUserResponse(user);
    }

    @Override
    public void addUser(UsersRequestDto usersRequestDto) {
        Users user = usersMapperInterface.userRequestToUser(usersRequestDto);
        Accounts account = new Accounts(null,0.0, State.Active,user);
        usersRepository.save(user);
        accountsRepository.save(account);
    }

    @Override
    public void updateUser(int id, UsersRequestDto usersRequestDto) {
        Users user = usersRepository.findById(id).get();
        if (usersRequestDto.getFirstName()!=null)
            user.setFirstName(usersRequestDto.getFirstName());
        if (usersRequestDto.getLastName()!=null)
            user.setLastName(usersRequestDto.getLastName());
        if (usersRequestDto.getEmail()!=null)
            user.setEmail(usersRequestDto.getEmail());
        if (usersRequestDto.getPhoneNumber()!=null)
            user.setPhoneNumber(usersRequestDto.getPhoneNumber());
        if (usersRequestDto.getDateOfBirth()!=null)
            user.setDateOfBirth(usersRequestDto.getDateOfBirth());
        if (usersRequestDto.getUserRolesIds()!=null && !usersRequestDto.getUserRolesIds().isEmpty())
            user.setUserRoles(usersMapperInterfaceImplim.turnIdsToRoles(usersRequestDto.getUserRolesIds()));
        if (usersRequestDto.getUserAccountsIds()!=null && !usersRequestDto.getUserAccountsIds().isEmpty())
            user.setUserAccounts(usersMapperInterfaceImplim.turnIdsToAccounts(usersRequestDto.getUserAccountsIds()));

        usersRepository.save(user);

    }

    @Override
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }
}
