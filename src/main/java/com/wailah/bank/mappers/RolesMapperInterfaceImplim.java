package com.wailah.bank.mappers;

import com.wailah.bank.dtos.RolesRequestDto;
import com.wailah.bank.dtos.RolesResponseDto;
import com.wailah.bank.entities.Roles;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RolesMapperInterfaceImplim implements RolesMapperInterface {
    @Override
    public Roles rolesRequestToRoles(RolesRequestDto rolesRequestDto) {
        Roles role = new Roles();
        BeanUtils.copyProperties(rolesRequestDto,role);
        return role;
    }

    @Override
    public RolesResponseDto rolesToRolesResponse(Roles roles) {
        RolesResponseDto rolesResponseDto =  new RolesResponseDto();
        BeanUtils.copyProperties(roles,rolesResponseDto);
        return rolesResponseDto;
    }
}
