package com.wailah.bank.services;

import com.wailah.bank.dtos.RolesRequestDto;
import com.wailah.bank.dtos.RolesResponseDto;

import java.util.List;

public interface RolesServiceInterface {

    //method to get all the roles
    public List<RolesResponseDto> getAllRoles();

    //method to get an role by it's id
    public RolesResponseDto getRoleById(int id);

    //method to add new user
    public void addRole(RolesRequestDto rolesRequestDto);

    //method to update an existing role
    public void updateRole(int id,RolesRequestDto rolesRequestDto);

    //method to delete an existing role
    public void deleteRole(int id);


}
