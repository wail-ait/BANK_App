package com.wailah.bank.mappers;

import com.wailah.bank.dtos.UsersRequestDto;
import com.wailah.bank.dtos.UsersResponseDto;
import com.wailah.bank.entities.Users;
import org.springframework.context.annotation.Bean;

public interface UsersMapperInterface {

    public Users userRequestToUser(UsersRequestDto usersRequestDto);

    public UsersResponseDto UserToUserResponse(Users users);

}
