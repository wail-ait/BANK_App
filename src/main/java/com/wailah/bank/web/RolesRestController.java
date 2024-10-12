package com.wailah.bank.web;

import com.wailah.bank.dtos.RolesRequestDto;
import com.wailah.bank.dtos.RolesResponseDto;
import com.wailah.bank.services.RolesServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RolesRestController {

    private final RolesServiceInterface rolesServiceInterface;

    public RolesRestController(RolesServiceInterface rolesServiceInterface) {
        this.rolesServiceInterface = rolesServiceInterface;
    }

    @GetMapping("/roles")
    public List<RolesResponseDto> getAll(){
        return rolesServiceInterface.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public RolesResponseDto getById(@PathVariable int id){
        return rolesServiceInterface.getRoleById(id);
    }

    @PostMapping("/roles")
    public void add(@RequestBody RolesRequestDto rolesRequestDto){
        rolesServiceInterface.addRole(rolesRequestDto);
    }

    @PutMapping("/roles/{id}")
    public void update(@PathVariable int id,@RequestBody RolesRequestDto rolesRequestDto){
        rolesServiceInterface.updateRole(id,rolesRequestDto);
    }

    @DeleteMapping("/roles/{id}")
    public void delete(@PathVariable int id){
        rolesServiceInterface.deleteRole(id);
    }
}
