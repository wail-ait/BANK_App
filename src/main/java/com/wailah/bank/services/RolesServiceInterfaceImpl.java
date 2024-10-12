package com.wailah.bank.services;

import com.wailah.bank.dtos.RolesRequestDto;
import com.wailah.bank.dtos.RolesResponseDto;
import com.wailah.bank.entities.Roles;
import com.wailah.bank.mappers.RolesMapperInterface;
import com.wailah.bank.repositories.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolesServiceInterfaceImpl implements RolesServiceInterface {

    private final RolesRepository rolesRepository;
    private final RolesMapperInterface rolesMapperInterface;

    public RolesServiceInterfaceImpl(RolesRepository rolesRepository, RolesMapperInterface rolesMapperInterface) {
        this.rolesRepository = rolesRepository;
        this.rolesMapperInterface = rolesMapperInterface;
    }

    @Override
    public List<RolesResponseDto> getAllRoles() {
        List<Roles> rolesList = rolesRepository.findAll();
        List<RolesResponseDto> rolesResponseList = new ArrayList<>();
        for (Roles role : rolesList){
            rolesResponseList.add(rolesMapperInterface.rolesToRolesResponse(role));
        }
        return rolesResponseList;
    }

    @Override
    public RolesResponseDto getRoleById(int id) {
        Roles role = rolesRepository.findById(id).get();
        return rolesMapperInterface.rolesToRolesResponse(role);
    }

    @Override
    public void addRole(RolesRequestDto rolesRequestDto) {
        Roles role = rolesMapperInterface.rolesRequestToRoles(rolesRequestDto);
        rolesRepository.save(role);
    }

    @Override
    public void updateRole(int id, RolesRequestDto rolesRequestDto) {
        Roles role = rolesRepository.findById(id).get();
        if (rolesRequestDto.getRoleName()!=null)
            role.setRoleName(rolesRequestDto.getRoleName());
        rolesRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        rolesRepository.deleteById(id);
    }
}
