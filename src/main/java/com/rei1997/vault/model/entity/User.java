package com.rei1997.vault.model.entity;

import java.time.LocalDateTime;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {


    @Id    
    @Indexed(unique = true)
    @NonNull
    private String email;
    @Indexed(unique = true)
    @NonNull
    private String userAccount;
    @NonNull
    private String userPassword;
    @NonNull
    private Integer depositAccount;
    @NonNull
    private Integer status;

    public User(String email, String userAccount, String userPassword, Integer depositAccount, Integer status) {
        this.email = email;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.depositAccount = depositAccount;
        this.status = status;
    }

    

}
