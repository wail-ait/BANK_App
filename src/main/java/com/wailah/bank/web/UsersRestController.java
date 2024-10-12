package com.wailah.bank.web;

import com.wailah.bank.dtos.UsersRequestDto;
import com.wailah.bank.dtos.UsersResponseDto;
import com.wailah.bank.services.UsersServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersRestController {

    private final UsersServiceInterface usersServiceInterface;

    public UsersRestController(UsersServiceInterface usersServiceInterface) {
        this.usersServiceInterface = usersServiceInterface;
    }

    @GetMapping("/user")
    public List<UsersResponseDto> getAll(){
        return usersServiceInterface.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UsersResponseDto getById(@PathVariable int id){
        return usersServiceInterface.getById(id);
    }

    @PostMapping("/user")
    public void add(@RequestBody UsersRequestDto usersRequestDto){
        usersServiceInterface.addUser(usersRequestDto);
    }

    @PutMapping("/user/{id}")
    public void update(@PathVariable int id, @RequestBody UsersRequestDto usersRequestDto){
        usersServiceInterface.updateUser(id,usersRequestDto);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id){
        usersServiceInterface.deleteUser(id);
    }

}
