package com.rei1997.vault.controller;

import java.util.List;
import java.util.Optional;

import com.rei1997.vault.model.entity.User;
import com.rei1997.vault.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
 
    @GetMapping
    public List<User> fetchAllUsers(){
        return userService.getAllUsers();

    }

    @GetMapping("/{email}")
    public Optional<User> getOneUserDetail(@PathVariable String email){
        return  userService.getOneUser(email);
    }

    


}
