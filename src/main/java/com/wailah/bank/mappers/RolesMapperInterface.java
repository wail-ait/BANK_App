package com.wailah.bank.mappers;

import com.wailah.bank.dtos.RolesRequestDto;
import com.wailah.bank.dtos.RolesResponseDto;
import com.wailah.bank.entities.Roles;

public interface RolesMapperInterface {

    public Roles rolesRequestToRoles(RolesRequestDto rolesRequestDto);

    public RolesResponseDto rolesToRolesResponse(Roles roles);

}
