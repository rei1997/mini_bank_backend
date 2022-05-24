package com.rei1997.vault.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rei1997.vault.model.entity.User;
import com.rei1997.vault.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;


@RestController
@RequestMapping(value="api/v1/user",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
 
    @GetMapping
    public List<User> fetchAllUsers(){
        return userService.getAllUsers();

    }

    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> getOneUserDetail(@PathVariable String email){
        return  ResponseEntity.ok().body(userService.getOneUser(email));
    }

    @PostMapping("/{email}")
    public void createUser(@RequestBody User user){
        String email=user.getEmail();

        if(!userService.getOneUser(email).isPresent()){
            try {
                userService.addUser(user);
            } catch (RuntimeException e) {
                System.out.println("addUser Exception"+e.toString());
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody User user){
        String email= user.getEmail();
        String userAccount= user.getUserAccount();
        String userPassword= user.getUserPassword();
        String result="9999";
        Map<String,String> token =new HashMap<String,String>();
        if(StringUtils.isBlank(email)||StringUtils.isBlank(userAccount)||StringUtils.isBlank(userPassword)){
            token.put("status", result);
            try {
                result = new ObjectMapper().writeValueAsString(token);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.badRequest().body(result);
        }else{
            try {
                result = new ObjectMapper().writeValueAsString(
                    userService.verifyUser(email, userAccount, userPassword)
                    );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(result);  
        }
    }   

    

}
