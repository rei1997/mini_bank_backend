package com.rei1997.vault.model.entity;

import java.time.LocalDateTime;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class UserProfile {
    
    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String email;
    private String email_bak;
    private String address;
    @NonNull
    private Integer gender;//0:male 1:female 2:other 3:unknown
    @NonNull
    private LocalDateTime create_time;
    @NonNull
    private LocalDateTime modify_time;

    public UserProfile(String email, String email_bak, String address, Integer gender, LocalDateTime create_time,
            LocalDateTime modify_time) {

        this.email = email;
        this.email_bak = email_bak;
        this.address = address;
        this.gender = gender;
        this.create_time = create_time;
        this.modify_time = modify_time;

    }

    

}
