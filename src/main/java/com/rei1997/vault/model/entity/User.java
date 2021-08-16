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


    @NonNull
    private String firstName;
    private String lastName;
    @Id    
    @Indexed(unique = true)
    @NonNull
    private String email;
    @Indexed(unique = true)
    private String cellPhone;
    @NonNull
    private String gender;
    private String address;
    @NonNull
    private LocalDateTime createTime;
    @NonNull
    private LocalDateTime modifyTime;
    @NonNull
    private Integer status;

    public User(String firstName,String lastName,String email,String cellPhone,
                    String gender,String address,LocalDateTime createTime,LocalDateTime modifyTime,Integer status){
                        this.firstName = firstName;
                        this.lastName = lastName;
                        this.email = email;
                        this.cellPhone = cellPhone;
                        this.gender = gender;
                        this. address = address;
                        this.createTime = createTime;
                        this.modifyTime = modifyTime;
                        this.status = status;

                    }
}
