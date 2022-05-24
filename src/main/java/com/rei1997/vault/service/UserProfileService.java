package com.rei1997.vault.service;



import java.util.List;
import java.util.Optional;


import com.rei1997.vault.model.entity.UserProfile;
import com.rei1997.vault.model.repository.UserProfileRepo;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProfileService {
    
    private final UserProfileRepo userProfileRepo;


    public List<UserProfile> getAllUsers(){
        return userProfileRepo.findAll();
    }


    public Integer getUserGender(String email){

        //0:male 1:female 2:other 3:unknown
        int gender= 3;
        Optional<Integer> userGender;
        userGender= userProfileRepo.findGenderByEmail(email);
        if (userGender.isPresent()){
            gender=userGender.get();
        }
        return gender;
    }

    public String getUserEmailBak(String email){
        String emailBak="";
        Optional<String> userEmailBak;
        userEmailBak= userProfileRepo.findEmailBakByEmail(email);
        if (userEmailBak.isPresent()){
            emailBak=userEmailBak.get();
        }
        return emailBak;
    }



}
