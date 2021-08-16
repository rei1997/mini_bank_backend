package com.rei1997.vault.service;

import java.util.List;
import java.util.Optional;

import com.rei1997.vault.model.entity.User;
import com.rei1997.vault.model.repository.UserRepo;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepo userRepo;


    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getOneUser(String email){
        return userRepo.findUserByEmail(email);
    }

}
